package com.training.fnsrv.task;

import com.training.fnsrv.model.IpRoute;
import lombok.extern.java.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Log
public class IpRouteTask extends Task {
    private final String COMMAND = "route";
    private final int TOKEN_NUM = 8;

    IpRouteTask() {
        setId(TaskExecutor.genNewTaskId());
        setCmd(COMMAND);
    }

    IpRouteTask(Long id) {
        setId(id);
        setCmd(COMMAND);
    }

    public void collect(InputStream inputStream) {
        //XXX: debug only. Use DB
        List<IpRoute>  routeList = new ArrayList<>();

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
                    use(Integer.parseInt(tokens[6]));
                    //TODO: add iface(). from IpInterface table;

            routeList.add(routeTableLine.build());
        }
        //XXX: debug only. Use DB
        log.info(Arrays.toString(routeList.toArray()));
    }
}
