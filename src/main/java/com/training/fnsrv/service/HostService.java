package com.training.fnsrv.service;

import com.training.fnsrv.model.Host;
import com.training.fnsrv.model.HostResponse;
import com.training.fnsrv.task.Task;
import com.training.fnsrv.task.HostTask;
import com.training.fnsrv.task.TaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HostService {
    @Autowired
    TaskExecutor taskExecutor;
    @Autowired
    private ApplicationContext applicationContext;

    public HostResponse getHostInterfaceRoute(Host host) {
        HostTask hostTask = new HostTask(host);
        /* TODO: Try to find better way to add beans for objects manually created with 'new' */
        applicationContext.getAutowireCapableBeanFactory().autowireBean(hostTask);
        hostTask.init();
        taskExecutor.executeTask(hostTask);

        HostResponse resp = new HostResponse();
        resp.setId(hostTask.getId());
        resp.setStatus(hostTask.getStatus());

        return resp;
    }
}
