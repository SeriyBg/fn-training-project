package com.training.fnsrv.task;

import com.training.fnsrv.model.Host;
import com.training.fnsrv.model.HostRequest;
import com.training.fnsrv.service.HostService;
import com.training.fnsrv.sshclient.SshClient;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.Iterator;

@Log
public class HostTask extends Task {
    @Getter private HostService hostService;
    private SshClient ssh;
    private HostRequest hostReq;
    @Getter private Host.Builder hostBuilder;

    public HostTask(HostRequest req, HostService service) {
        hostReq = req;
        hostService = service;
        setId(TaskExecutor.genNewTaskId());

        ssh = new SshClient(hostReq.getAddr(), hostReq.getUser(), hostReq.getPassword());

        hostBuilder = new Host.Builder();

        IpInterfaceTask ipInterfaceTask = new IpInterfaceTask(getId(), this);
        addNextTask(ipInterfaceTask);

        IpRouteTask ipRouteTask = new IpRouteTask(getId(), this);
        addNextTask(ipRouteTask);
    }

    @Override
    public void run() {
        hostBuilder.requestId(getId()).
                ipAddress(hostReq.getAddr()).
                user(hostReq.getUser()).
                password(hostReq.getPassword());

        ssh.connect();

        Iterator<Task> iter = iterator();
        while (iter.hasNext()) {
            Task task = iter.next();

            ssh.exec(task.getCmd(), task);
            task.setStatus(TaskStatus.DONE);
            log.info(String.format("RUN task with parentid='%d'; id='%d'", task.getParentId(), task.getId()));
        }
        ssh.disconnect();
    }
}
