package com.training.fnsrv.controller;

import com.training.fnsrv.model.IpRoute;
import com.training.fnsrv.service.IpRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IpRouteController {
    @Autowired
    IpRouteService ipRouteService;

    @RequestMapping(value = "/routes/all/", method = RequestMethod.GET)
    public ResponseEntity<List<IpRoute>> getAllIpRoutes() {
        List<IpRoute> routes = ipRouteService.getAll();

        return new ResponseEntity<List<IpRoute>>(routes, HttpStatus.OK);
    }

    @RequestMapping(value = "/routes/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<IpRoute>> getById(@PathVariable String id) {
        List<IpRoute> routes = ipRouteService.getById(Long.valueOf(id));

        return new ResponseEntity<List<IpRoute>>(routes, HttpStatus.OK);
    }
}
