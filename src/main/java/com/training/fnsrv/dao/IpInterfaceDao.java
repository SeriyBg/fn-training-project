package com.training.fnsrv.dao;

import com.training.fnsrv.model.IpInterface;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IpInterfaceDao extends CrudRepository<IpInterface, Long> {
}
