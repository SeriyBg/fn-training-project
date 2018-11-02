package com.training.fnsrv.service;

import com.training.fnsrv.dao.IpInterfaceDao;
import com.training.fnsrv.model.IpInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IpInterfaceService {
    @Autowired
    private IpInterfaceDao ipInterfaceDao;
    @Autowired
    private IpAddressService ipAddressService;

    public void save(IpInterface ipInterface) {
        try {
            ipAddressService.save(ipInterface.getIpAddress());
            ipInterfaceDao.save(ipInterface);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<IpInterface> getAll() {
        List<IpInterface> intfList = new ArrayList<>();
        for (IpInterface intf : ipInterfaceDao.findAll()) {
            intfList.add(intf);
        }
        return intfList;
    }
}
