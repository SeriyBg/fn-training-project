package com.training.fnsrv.task;

import com.training.fnsrv.model.IpAddress;
import com.training.fnsrv.model.IpInterface;
import lombok.extern.java.Log;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
public class IpInterfaceTask extends Task {
    private HostTask hostTask;
    private static final String COMMAND = "ifconfig";
    private static final Pattern NAME_PATTERN = Pattern.compile("^[\\w]+");
    private static final Pattern MAC_PATTERN = Pattern.compile("HWaddr\\s+([0-9a-fA-F:-]+)");
    private static final Pattern IPADDR_PATTERN = Pattern.compile("inet addr:([0-9.]+)");
    private static final Pattern NETMASK_PATTERN = Pattern.compile("Mask:([0-9.]+)");
    private static final Pattern MTU_PATTERN = Pattern.compile("MTU:([0-9.]+)");

    IpInterfaceTask(Long id, HostTask hostTask) {
        this.hostTask = hostTask;
        setId(id);
        setCmd(COMMAND);
    }

    private void runNextTask() {
        IpRouteTask ipRouteTask = new IpRouteTask(hostTask.getId(), hostTask);
        hostTask.getHostService().getTaskExecutor().executeTask(ipRouteTask);
    }

    public void collect(InputStream inputStream) {
        Matcher matcher;
        IpInterface.Builder intf = new IpInterface.Builder();
        IpAddress.Builder ipaddr = new IpAddress.Builder();

        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            log.info(line);

            if (intf.getName() == null) {

                matcher = NAME_PATTERN.matcher(line);
                if (matcher.find()) {
                    /* Skip localhost interface */
                    if (matcher.group(0).equals("lo")) {
                        continue;
                    }
                    intf.name(matcher.group(0));
                }
            }

            if (intf.getName() != null && intf.getMacAddress() == null) {
                matcher = MAC_PATTERN.matcher(line);
                if (matcher.find()) {
                    intf.macAddress(matcher.group(1));
                }
            }

            if (intf.getMacAddress() != null && ipaddr.getAddr() == null) {
                matcher = IPADDR_PATTERN.matcher(line);
                if (matcher.find()) {
                    ipaddr.addr(matcher.group(1));
                }
            }

            /* Don't save netmask in case in interface doesn't have IPv4 address */
            if (ipaddr.getAddr() != null && ipaddr.getNetmask() == null) {
                matcher = NETMASK_PATTERN.matcher(line);
                if (matcher.find()) {
                    ipaddr.netmask(matcher.group(1));
                }
            }

            if (intf.getMacAddress() != null && intf.getMTU() == 0) {
                matcher = MTU_PATTERN.matcher(line);
                if (matcher.find()) {
                    intf.MTU(Integer.parseInt(matcher.group(1)));
                }
            }

            if (line.isEmpty() && intf.getName() != null) {
                intf.ipAddress(ipaddr.build());
                hostTask.getHostBuilder().ipInterfaces(intf.build());
                if (scanner.hasNext()) {
                    intf = new IpInterface.Builder();
                    ipaddr = new IpAddress.Builder();
                }
            }
        }
        log.info(String.format("Finish ipInterfaceTask with id='%d'", getId()));
        runNextTask();
    }

    public void run() {
        log.info(String.format("Running ipInterfaceTask with id='%d'", getId()));
        hostTask.getSsh().connect();
        hostTask.getSsh().exec(getCmd(), this);
        hostTask.getSsh().disconnect();
    }
}
