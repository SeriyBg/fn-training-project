package com.training.fnsrv.DB;

import com.training.fnsrv.model.Host;
import com.training.fnsrv.model.IpInterface;
import com.training.fnsrv.model.IpRoute;

import java.util.List;

public interface DBInterface {
    void saveHost(Host host);
    Host getHostById(Long id);

    void saveIpInterface(IpInterface ipInterface);
    List<IpInterface> getAllIpInterfaces();
    List<IpInterface> getAllIpInterfacesById(Long id);

    void saveIpRoute(IpRoute ipRoute);
    List<IpRoute> getAllIpRoutesById(Long id);
}
