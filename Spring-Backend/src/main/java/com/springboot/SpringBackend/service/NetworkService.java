package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Network;

import java.util.List;
import java.util.Optional;

public interface NetworkService {
    List<Network> getAllNetworks();

    Optional<Network> getNetworkById(Long id);

    Network createNetwork(Network x);

    Network updateNetwork(Network x);

    void deleteNetwork(Network x);

    void deleteAllNetworks();
}
