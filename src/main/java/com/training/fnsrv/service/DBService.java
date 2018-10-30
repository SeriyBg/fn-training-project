package com.training.fnsrv.service;

import com.training.fnsrv.DB.MemoryDB;
import com.training.fnsrv.model.Host;
import com.training.fnsrv.model.IpInterface;
import com.training.fnsrv.model.IpRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBService {
    @Autowired
    MemoryDB db;

    public void saveHost(Host host) {
        db.saveHost(host);
    }

    public Host getHostById(Long id) {
        return db.getHostById(id);
    }

    public void saveIpInterface(IpInterface ipInterface) {
        db.saveIpInterface(ipInterface);
    }

    public List<IpInterface> getAllIpInterfaces() {
        return db.getAllIpInterfaces();
    }

    public List<IpInterface> getAllIpInterfacesById(Long id) {
        return db.getAllIpInterfacesById(id);
    }

    public IpInterface getIpInterfacesByIdAndName(Long id, String name) {
        return db.getIpInterfacesByIdAndName(id, name);
    }

    public void saveIpRoute(IpRoute ipRoute) {
        db.saveIpRoute(ipRoute);
    }

    public List<IpRoute> getAllIpRoutesById(Long id){
        return db.getAllIpRoutesById(id);
    }
}
