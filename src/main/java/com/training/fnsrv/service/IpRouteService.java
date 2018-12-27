package com.training.fnsrv.service;

import com.training.fnsrv.dao.inmemory.InMemoryIpRouteDao;
import com.training.fnsrv.model.IpRoute;
import com.training.fnsrv.model.RouteCmd;
import com.training.fnsrv.model.RouteCmdRespose;
import com.training.fnsrv.task.IpRouteCmdTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IpRouteService {
    @Autowired
    private /*IpRouteDao*/InMemoryIpRouteDao ipRouteDao;

    public void save(IpRoute ipRoute) {
        try {
            ipRouteDao.save(ipRoute);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<IpRoute> getAll() {
        return StreamSupport.stream(ipRouteDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public RouteCmdRespose addRemoteHostRoute(RouteCmd routeCmd) {
        IpRouteCmdTask ipRouteCmdTask = new IpRouteCmdTask(routeCmd, this);
        ipRouteCmdTask.run();

        RouteCmdRespose resp = new RouteCmdRespose();
        resp.setStatus(ipRouteCmdTask.getStatus());

        return resp;
    }
}
