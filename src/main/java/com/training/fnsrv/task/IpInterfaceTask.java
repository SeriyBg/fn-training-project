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
    private final String COMMAND = "ifconfig";
    private final String NAME_PATTERN = "^[\\w]+";
    private final String MAC_PATTERN = "HWaddr\\s+([0-9a-fA-F:-]+)";
    private final String IPADDR_PATTERN = "inet addr:([0-9.]+)";
    private final String NETMASK_PATTERN = "Mask:([0-9.]+)";
    private final String MTU_PATTERN = "MTU:([0-9.]+)";

    IpInterfaceTask(Long id, HostTask hostTask) {
        this.hostTask = hostTask;
        setId(id);
        setCmd(COMMAND);
    }

    public void collect(InputStream inputStream) {
        //TODO: make this patterns static final;
        Pattern namePattern = Pattern.compile(NAME_PATTERN);
        Pattern macPattern = Pattern.compile(MAC_PATTERN);
        Pattern ipaddrPattern = Pattern.compile(IPADDR_PATTERN);
        Pattern netmaskPattern = Pattern.compile(NETMASK_PATTERN);
        Pattern mtuPattern = Pattern.compile(MTU_PATTERN);
        Matcher matcher;

        IpInterface.Builder intf = new IpInterface.Builder();
        IpAddress.Builder ipaddr = new IpAddress.Builder();

        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            log.info(line);

            if (intf.getName() == null) {

                matcher = namePattern.matcher(line);
                if (matcher.find()) {
                    /* Skip localhost interface */
                    if (matcher.group(0).equals("lo")) {
                        continue;
                    }
                    intf.name(matcher.group(0));
                }
            }

            if (intf.getName() != null && intf.getMacAddress() == null) {
                matcher = macPattern.matcher(line);
                if (matcher.find()) {
                    intf.macAddress(matcher.group(1));
                }
            }

            if (intf.getMacAddress() != null && ipaddr.getAddr() == null) {
                matcher = ipaddrPattern.matcher(line);
                if (matcher.find()) {
                    ipaddr.addr(matcher.group(1));
                }
            }

            /* Don't save netmask in case in interface doesn't have IPv4 address */
            if (ipaddr.getAddr() != null && ipaddr.getNetmask() == null) {
                matcher = netmaskPattern.matcher(line);
                if (matcher.find()) {
                    ipaddr.netmask(matcher.group(1));
                }
            }

            if (intf.getMacAddress() != null && intf.getMTU() == 0) {
                matcher = mtuPattern.matcher(line);
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
        //TODO: run ipRouteTask
    }
}
