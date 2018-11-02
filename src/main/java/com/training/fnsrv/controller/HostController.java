package com.training.fnsrv.controller;

import com.training.fnsrv.model.Host;
import com.training.fnsrv.model.HostRequest;
import com.training.fnsrv.model.HostResponse;
import com.training.fnsrv.service.HostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HostController {
    @Autowired
    private HostService hostService;

    @RequestMapping(value = "/host/get_info/", method = RequestMethod.POST)
    public ResponseEntity<HostResponse> addRoute(@RequestBody HostRequest hostReq) {
        HostResponse resp = hostService.getHostInterfaceRoute(hostReq);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @RequestMapping(value = "/host/all/", method = RequestMethod.GET)
    public ResponseEntity<List<Host>> getAllHosts() {
        List<Host> resp = hostService.getAll();

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
