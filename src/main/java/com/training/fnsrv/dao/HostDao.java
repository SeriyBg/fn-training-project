package com.training.fnsrv.dao;

import com.training.fnsrv.model.Host;
import org.springframework.data.repository.CrudRepository;

public interface HostDao extends CrudRepository<Host, Long> {
}
