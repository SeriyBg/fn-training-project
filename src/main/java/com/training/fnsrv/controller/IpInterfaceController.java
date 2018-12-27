package com.training.fnsrv.controller;

import com.training.fnsrv.model.IpInterface;
import com.training.fnsrv.service.IpInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IpInterfaceController {
    @Autowired
    private IpInterfaceService ipInterfaceService;

    @RequestMapping(value = "/interfaces/all", method = RequestMethod.GET)
    public List<IpInterface> getAllIpInterfaces() {
        return ipInterfaceService.getAll();
    }
}
