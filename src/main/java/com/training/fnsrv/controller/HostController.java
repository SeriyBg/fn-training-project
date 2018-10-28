package com.training.fnsrv.controller;

import com.training.fnsrv.model.Host;
import com.training.fnsrv.model.HostResponse;
import com.training.fnsrv.service.HostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HostController {

    @Autowired
    HostService hostService;

    @RequestMapping(value = "/host/get_info/", method = RequestMethod.POST)
    public ResponseEntity<HostResponse> addRoute(@RequestBody Host host) {
        HostResponse resp = hostService.getHostInterfaceRoute(host);

        return new ResponseEntity<HostResponse>(resp, HttpStatus.OK);
    }
}
