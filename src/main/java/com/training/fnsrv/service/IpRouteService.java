package com.training.fnsrv.service;

import com.training.fnsrv.model.IpRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IpRouteService {
    @Autowired
    DBService db;

    public List<IpRoute> getAll() {
        return db.getAllIpRoutes();
    }

    public List<IpRoute> getById(Long id) {
        return db.getAllIpRoutesById(id);
    }
}
