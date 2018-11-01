package com.training.fnsrv.Dao;

import com.training.fnsrv.model.Host;
import org.springframework.data.repository.CrudRepository;

public interface HostDao extends CrudRepository<Host, Long> {
    Host findByUser(String user);
    Host findByIpAddress(String ipAddress);
}
