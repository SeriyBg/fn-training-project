package com.training.fnsrv.controller;

import com.training.fnsrv.model.IpInterface;
import com.training.fnsrv.service.IpInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IpInterfaceController {
    @Autowired
    IpInterfaceService ipInterfaceService;

    @RequestMapping(value = "/interfaces/all/", method = RequestMethod.GET)
    public ResponseEntity<List<IpInterface>> getAllIpInterfaces() {
        List<IpInterface> interfaces = ipInterfaceService.getAll();

        return new ResponseEntity<List<IpInterface>>(interfaces, HttpStatus.OK);
    }

    @RequestMapping(value = "/interfaces/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<IpInterface>> getById(@PathVariable String id) {
        List<IpInterface> interfaces = ipInterfaceService.getById(Long.valueOf(id));

        return new ResponseEntity<List<IpInterface>>(interfaces, HttpStatus.OK);
    }
}
