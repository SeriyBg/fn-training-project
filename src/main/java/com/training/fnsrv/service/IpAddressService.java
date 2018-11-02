package com.training.fnsrv.service;

import com.training.fnsrv.dao.IpAddressDao;
import com.training.fnsrv.model.IpAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpAddressService {
    @Autowired
    private IpAddressDao ipAddressDao;

    public void save(IpAddress ipAddress) {
        ipAddressDao.save(ipAddress);
    }
}
