package com.training.fnsrv.dao;

import com.training.fnsrv.model.IpAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IpAddressDao extends CrudRepository<IpAddress, Long> {
}
