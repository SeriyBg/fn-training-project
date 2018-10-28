package com.training.fnsrv.task;

import com.training.fnsrv.utils.Collector;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public abstract class Task implements Runnable, Collector {
    private Long id;
    private Long parentId;
    private TaskStatus status;
    private String cmd;
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) private List<Task> subTasks;

    {
        id = 0L;
        parentId = 0L;
        status = TaskStatus.NEW;
        subTasks = new ArrayList<>();
    }

    protected void addNextTask(Task newTask) {
        newTask.setParentId(id);
        subTasks.add(newTask);
    }

    protected Iterator<Task> iterator() {
        return subTasks.iterator();
    }

    public void collect(InputStream inputStream) {
        System.out.printf("Task:<collect> \n");
    }

    public void run() {
        System.out.printf("Task:<run> id: %s", getId());
    }
}