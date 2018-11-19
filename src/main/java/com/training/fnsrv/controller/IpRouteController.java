package com.training.fnsrv.controller;

import com.training.fnsrv.model.IpRoute;
import com.training.fnsrv.model.RouteCmd;
import com.training.fnsrv.model.RouteCmdRespose;
import com.training.fnsrv.service.IpRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IpRouteController {
    @Autowired
    private IpRouteService ipRouteService;

    @RequestMapping(value = "/routes/all", method = RequestMethod.GET)
    public List<IpRoute> getAllIpRoutes() {
        return ipRouteService.getAll();
    }

    @RequestMapping(value = "/routes/cmd", method = RequestMethod.POST)
    public RouteCmdRespose addRoute(@RequestBody RouteCmd routeCmd) {
        return ipRouteService.addRemoteHostRoute(routeCmd);
    }
}
