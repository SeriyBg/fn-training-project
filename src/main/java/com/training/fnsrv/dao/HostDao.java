package com.training.fnsrv.dao;

import com.training.fnsrv.model.Host;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HostDao extends CrudRepository<Host, Long> {
}
