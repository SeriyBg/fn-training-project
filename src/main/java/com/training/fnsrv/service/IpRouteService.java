package com.training.fnsrv.service;

import com.training.fnsrv.dao.IpRouteDao;
import com.training.fnsrv.model.IpRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IpRouteService {
    @Autowired
    private IpRouteDao ipRouteDao;

    public void save(IpRoute ipRoute) {
        try {
            ipRouteDao.save(ipRoute);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<IpRoute> getAll() {
        List<IpRoute> routeList = new ArrayList<>();
        for (IpRoute route : ipRouteDao.findAll()) {
            routeList.add(route);
        }
        return routeList;
    }
}
