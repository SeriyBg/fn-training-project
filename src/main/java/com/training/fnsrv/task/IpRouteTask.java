package com.training.fnsrv.task;

import java.io.InputStream;
import java.util.Scanner;

public class IpRouteTask extends Task {
    private final String COMMAND = "route";

    IpRouteTask() {
        setId(TaskExecutor.genNewTaskId());
        setCmd(COMMAND);
    }

    IpRouteTask(Long id) {
        setId(id);
        setCmd(COMMAND);
    }

    public void collect(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }
}
