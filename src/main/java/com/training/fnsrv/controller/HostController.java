package com.training.fnsrv.controller;

import com.training.fnsrv.model.Host;
import com.training.fnsrv.model.HostRequest;
import com.training.fnsrv.model.HostResponse;
import com.training.fnsrv.service.HostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HostController {
    @Autowired
    private HostService hostService;

    @RequestMapping(value = "/host", method = RequestMethod.POST)
    public HostResponse addRoute(@RequestBody HostRequest hostReq) {
        return hostService.getHostInterfaceRoute(hostReq);
    }

    @RequestMapping(value = "/host/all", method = RequestMethod.GET)
    public List<Host> getAllHosts() {
        return hostService.getAll();
    }
}
