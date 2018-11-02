package com.training.fnsrv.task;

import com.training.fnsrv.model.IpInterface;
import com.training.fnsrv.model.IpRoute;
import lombok.extern.java.Log;

import java.io.InputStream;
import java.util.Scanner;

@Log
public class IpRouteTask extends Task {
    private HostTask hostTask;
    private final String COMMAND = "route";
    private final int TOKEN_NUM = 8;

    IpRouteTask(Long id, HostTask hostTask) {
        this.hostTask = hostTask;
        setId(id);
        setCmd(COMMAND);
    }

    private IpInterface getIpInterfaceByName(String name) {
        for (IpInterface intf : hostTask.getHostBuilder().getIpInterfaces()) {
            if (intf.getName().equals(name)) {
                return intf;
            }
        }
        return null;
    }

    public void collect(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            log.info(line);

            if (line.isEmpty() || line.contains("Kernel") || line.contains("Destination")) {
                continue;
            }

            String[] tokens = line.split(" +");
            if (tokens.length != TOKEN_NUM) {
                log.warning("Bad route line");
                continue;
            }

            IpRoute.Builder routeTableLine = new IpRoute.Builder();
            routeTableLine.
                    destination(tokens[0]).
                    gateway(tokens[1]).
                    genmask(tokens[2]).
                    flags(tokens[3]).
                    metric(Integer.parseInt(tokens[4])).
                    refs(Integer.parseInt(tokens[5])).
                    use(Integer.parseInt(tokens[6])).
                    iface(getIpInterfaceByName(tokens[7]));

            hostTask.getHostBuilder().
                    ipRoutes(routeTableLine.build());
        }
        hostTask.getHostService().
                save(hostTask.getHostBuilder().build());
    }
}
