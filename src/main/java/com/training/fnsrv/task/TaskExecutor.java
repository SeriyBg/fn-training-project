package com.training.fnsrv.task;

import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class TaskExecutor {
    private static Long ids;
    private final ThreadPoolExecutor executor;

    static {
        ids = 0L;
    }

    public TaskExecutor() {
        executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        System.out.printf("TaskExecutor: Init. Available Processors: %d \n", Runtime.getRuntime().availableProcessors());
    }

    public Task executeTask(Task task) {
        System.out.printf("TaskExecutor: got new Task\n");

        task.setStatus(TaskStatus.INPROGRESS);
        executor.execute(task);

        System.out.printf("TaskExecutor: Pool size: %d\n", executor.getPoolSize());
        System.out.printf("TaskExecutor: Active count: %d\n", executor.getActiveCount());
        System.out.printf("TaskExecutor: Task count: %d\n", executor.getTaskCount());
        System.out.printf("TaskExecutor: Completed tasks: %d\n", executor.getPoolSize());

        return task;
    }

    public void stopTasks() {
        executor.shutdown();
    }

    public static Long genNewTaskId() {
        return ++ids;
    }
}