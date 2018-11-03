package com.training.fnsrv.service;

import com.training.fnsrv.dao.HostDao;
import com.training.fnsrv.model.*;
import com.training.fnsrv.task.HostTask;
import com.training.fnsrv.task.TaskExecutor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HostService {
    @Autowired
    @Getter
    private TaskExecutor taskExecutor;
    @Autowired
    private HostDao hostDao;
    @Autowired
    private IpInterfaceService ipInterfaceService;
    @Autowired
    private IpRouteService ipRouteService;

    public HostResponse getHostInterfaceRoute(HostRequest hostReq) {
        HostTask hostTask = new HostTask(hostReq, this);
        taskExecutor.executeTask(hostTask);

        HostResponse resp = new HostResponse();
        resp.setId(hostTask.getId());
        resp.setStatus(hostTask.getStatus());

        return resp;
    }

    public void save(Host host) {
        try {
            for (IpInterface intf: host.getIpInterfaces()) {
                ipInterfaceService.save(intf);
            }
            for (IpRoute route: host.getIpRoutes()) {
                ipRouteService.save(route);
            }
            hostDao.save(host);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Host> getAll() {
        List<Host> hosts = new ArrayList<>();
        for (Host host : hostDao.findAll()) {
            hosts.add(host);
        }
        return hosts;
    }
}
