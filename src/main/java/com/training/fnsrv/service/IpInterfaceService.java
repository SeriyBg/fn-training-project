package com.training.fnsrv.service;

import com.training.fnsrv.Dao.IpInterfaceDao;
import com.training.fnsrv.model.IpInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IpInterfaceService {
    @Autowired
    IpInterfaceDao ipInterfaceDao;
    @Autowired
    IpAddressService ipAddressService;

    //TODO: A lot of boilerplate code
    public void save(IpInterface ipInterface) {
        ipAddressService.save(ipInterface.getIpAddress());
        ipInterfaceDao.save(ipInterface);
    }

    public List<IpInterface> getAll() {
        List<IpInterface> intfList = new ArrayList<>();
        for (IpInterface intf : ipInterfaceDao.findAll()) {
            intfList.add(intf);
        }
        return intfList;
    }

    public List<IpInterface> getById(Long id) {
        List<IpInterface> intfList = new ArrayList<>();
        for (IpInterface intf : ipInterfaceDao.findAll()) {
            if (intf.getReqId() == id) {
                intfList.add(intf);
            }
        }
        return intfList;
    }

    public IpInterface getByIdAndName(Long id, String name) {
        for (IpInterface intf : ipInterfaceDao.findAll()) {
            if (intf.getName().equals(name)) {
                return intf;
            }
        }

        //TODO: null or new IpInterface?
        return null;
    }

}
