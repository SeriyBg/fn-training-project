package com.training.fnsrv.Dao;

import com.training.fnsrv.model.IpInterface;
import org.springframework.data.repository.CrudRepository;

public interface IpInterfaceDao extends CrudRepository<IpInterface, Long> {
    Iterable<IpInterface> findByName(String name);
}
