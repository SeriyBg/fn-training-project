package com.training.fnsrv.task;

import com.training.fnsrv.model.Host;
import com.training.fnsrv.model.HostRequest;
import com.training.fnsrv.service.HostService;
import com.training.fnsrv.sshclient.SshClient;
import com.training.fnsrv.utils.Request;
import lombok.Getter;
import lombok.extern.java.Log;

@Log
public class HostTask extends Task {
    @Getter private HostService hostService;
    @Getter private SshClient ssh;
    private HostRequest hostReq;
    @Getter private Host.Builder hostBuilder;

    public HostTask(HostRequest req, HostService service) {
        hostReq = req;
        hostService = service;
        setId(Request.genRequestId());
        hostBuilder = new Host.Builder();

        ssh = new SshClient(hostReq.getAddr(), hostReq.getUser(), hostReq.getPassword());
    }

    @Override
    public void run() {
        log.info(String.format("Running HostTask with id='%d'", getId()));
        setStatus(TaskStatus.INPROGRESS);
        hostBuilder.requestId(getId()).
                ipAddress(hostReq.getAddr()).
                user(hostReq.getUser()).
                password(hostReq.getPassword());

        IpInterfaceTask ipInterfaceTask = new IpInterfaceTask(getId(), this);
        ipInterfaceTask.run();

        IpRouteTask ipRouteTask = new IpRouteTask(getId(), this);
        ipRouteTask.run();

        hostService.save(hostBuilder.build());
        setStatus(TaskStatus.DONE);
        log.info(String.format("Done HostTask with id='%d'", getId()));
    }
}
