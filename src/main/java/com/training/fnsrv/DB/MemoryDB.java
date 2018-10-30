package com.training.fnsrv.DB;

import com.training.fnsrv.model.GenModel;
import com.training.fnsrv.model.Host;
import com.training.fnsrv.model.IpInterface;
import com.training.fnsrv.model.IpRoute;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*XXX: This DB not thread safe at all */
@Component
public class MemoryDB implements DBInterface {
    private List<Host> hostTable = new CopyOnWriteArrayList<>();
    private List<IpInterface> intfTable = new CopyOnWriteArrayList<>();
    private List<IpRoute> routeTable = new CopyOnWriteArrayList<>();

    private <T> void saveRecord(T record, List<T> table) {
        table.add(record);
    }

    /*XXX: bad practice to do like this, as intfTable can be changed from another thread */
    private <T extends GenModel> List<T> _getAllIpInterfacesById(Long id, List<T> table) {
        List<T> respList = new ArrayList<>();

        for (T intf : table) {
            if (intf.getReqId() == id) {
                respList.add(intf);
            }
        }

        return respList;
    }

    public void saveHost(Host host) {
        saveRecord(host, hostTable);
    }

    public Host getHostById(Long id) {
        for (Host host: hostTable) {
            if (host.getReqId() == id) {
                return host;
            }
        }

        //TODO: new Host or null?
        Host.Builder newHost = new Host.Builder();
        return newHost.build();
    }

    public void saveIpInterface(IpInterface ipInterface) {
        saveRecord(ipInterface, intfTable);
    }

    public List<IpInterface> getAllIpInterfaces() {
        return intfTable;
    }

    public List<IpInterface> getAllIpInterfacesById(Long id) {
        return _getAllIpInterfacesById(id, intfTable);
    }

    public IpInterface getIpInterfacesByIdAndName(Long id, String name) {
        List<IpInterface> intfList = getAllIpInterfacesById(id);

        for (IpInterface intf : intfList) {
            if (intf.getName().equals(name)) {
                return intf;
            }
        }

        //TODO: null or new IpInterface?
        return null;
    }

    public void saveIpRoute(IpRoute ipRoute) {
        saveRecord(ipRoute, routeTable);
    }

    public List<IpRoute> getAllIpRoutes() {
        return routeTable;
    }

    public List<IpRoute> getAllIpRoutesById(Long id) {
        return _getAllIpInterfacesById(id, routeTable);
    }
}
