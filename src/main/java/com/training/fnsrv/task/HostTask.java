package com.training.fnsrv.task;

import com.training.fnsrv.model.Host;
import com.training.fnsrv.model.HostRequest;
import com.training.fnsrv.service.HostService;
import com.training.fnsrv.sshclient.SshClient;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Iterator;

@Log
public class HostTask extends Task {
    @Autowired
    HostService hostService;
    @Autowired
    private ApplicationContext applicationContext;
    private SshClient ssh;
    private HostRequest hostReq;

    public HostTask(HostRequest req) {
        hostReq = req;
        setId(TaskExecutor.genNewTaskId());

        ssh = new SshClient(hostReq.getAddr(), hostReq.getUser(), hostReq.getPassword());

    }

    public void init() {
        /* TODO: Try to find better way to add beans for objects which are manually created with 'new' */
        IpInterfaceTask ipInterfaceTask = new IpInterfaceTask(getId());
        applicationContext.getAutowireCapableBeanFactory().autowireBean(ipInterfaceTask);
        addNextTask(ipInterfaceTask);

        IpRouteTask ipRouteTask = new IpRouteTask(getId());
        applicationContext.getAutowireCapableBeanFactory().autowireBean(ipRouteTask);
        addNextTask(ipRouteTask);

        Host.Builder host = new Host.Builder();
        host.reqId(getId()).
                ipAddress(hostReq.getAddr()).
                user(hostReq.getUser()).
                password(hostReq.getPassword());

        hostService.save(host.build());
    }

    @Override
    public void run() {
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
