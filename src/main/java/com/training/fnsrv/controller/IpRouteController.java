package com.training.fnsrv.controller;

import com.training.fnsrv.model.IpRoute;
import com.training.fnsrv.service.IpRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IpRouteController {
    @Autowired
    private IpRouteService ipRouteService;

    @RequestMapping(value = "/routes/all/", method = RequestMethod.GET)
    public ResponseEntity<List<IpRoute>> getAllIpRoutes() {
        List<IpRoute> routes = ipRouteService.getAll();

        return new ResponseEntity<>(routes, HttpStatus.OK);
    }
}