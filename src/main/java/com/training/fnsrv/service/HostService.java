package com.training.fnsrv.service;

import com.training.fnsrv.model.Host;
import com.training.fnsrv.model.HostResponse;
import com.training.fnsrv.task.Task;
import com.training.fnsrv.task.HostTask;
import com.training.fnsrv.task.TaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HostService {
    @Autowired
    TaskExecutor taskExecutor;

    public HostResponse getHostInterfaceRoute(Host host) {
        Task hostTask = new HostTask(host);
        taskExecutor.executeTask(hostTask);

        HostResponse resp = new HostResponse();
        resp.setId(hostTask.getId());
        resp.setStatus(hostTask.getStatus());

        return resp;
    }
}
