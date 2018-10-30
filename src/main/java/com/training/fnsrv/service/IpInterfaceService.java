package com.training.fnsrv.service;

import com.training.fnsrv.model.IpInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IpInterfaceService {
    @Autowired
    DBService db;

    public List<IpInterface> getAll() {
        return db.getAllIpInterfaces();
    }

    public List<IpInterface> getById(Long id) {
        return db.getAllIpInterfacesById(id);
    }
}
