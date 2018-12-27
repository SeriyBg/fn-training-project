package com.training.fnsrv.dao;

import com.training.fnsrv.model.IpRoute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IpRouteDao extends CrudRepository<IpRoute, Long> {
}
