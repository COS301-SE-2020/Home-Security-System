package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Network;
import com.springboot.SpringBackend.model.Notification;
import com.springboot.SpringBackend.repository.NetworkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("NetworkService")
@Transactional
public class NetworkServiceImpl implements NetworkService {
    private final NetworkRepo repo;

    @Autowired
    public NetworkServiceImpl(NetworkRepo nRepo)
    {
        this.repo = nRepo;
    }

    @Override
    public List<Network> getAllNetworks() {
        return (List<Network>) this.repo.findAll();
    }

    @Override
    public Optional<Network> getNetworkById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Network createNetwork(Network x) {
        return this.repo.save(x);
    }

    @Override
    public Network updateNetwork(Network x) {
        return this.repo.save(x);
    }

    @Override
    public void deleteNetwork(Network x) {
        this.repo.delete(x);
    }

    @Override
    public void deleteAllNetworks() {
        this.repo.deleteAll();
    }
}
