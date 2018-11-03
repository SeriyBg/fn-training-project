package com.training.fnsrv.dao;

import com.training.fnsrv.model.Host;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryHostDao implements HostDao {
    private List<Host> hosts = new ArrayList<>();

    @Override
    public Host save(Host host) {
        hosts.add(host);
        return host;
    }

    @Override
    public <S extends Host> List<S> saveAll(Iterable<S> hosts) {
        return null;
    }

    @Override
    public Optional<Host> findById(Long id) {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return true;
    }

    @Override
    public Iterable<Host> findAll() {
        return hosts;
    }

    @Override
    public Iterable<Host> findAllById(Iterable<Long> ids) {
        return null;
    }

    @Override
    public long count() {
        return 0L;
    }

    @Override
    public void deleteById(Long id) { }

    @Override
    public void delete(Host host) { }

    @Override
    public void deleteAll(Iterable<? extends Host> hosts) { }

    @Override
    public void deleteAll() { }
}
