package com.training.fnsrv.dao;

import com.training.fnsrv.model.IpAddress;
import org.springframework.data.repository.CrudRepository;

public interface IpAddressDao extends CrudRepository<IpAddress, Long> {
}
