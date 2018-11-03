package com.training.fnsrv.service;

import com.training.fnsrv.dao.InMemoryIpAddressDao;
import com.training.fnsrv.model.IpAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpAddressService {
    @Autowired
    private /*IpAddressDao*/InMemoryIpAddressDao ipAddressDao;

    public void save(IpAddress ipAddress) {
        try {
            ipAddressDao.save(ipAddress);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
