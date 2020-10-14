package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.model.Network;
import com.springboot.SpringBackend.repository.NetworkRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NetworkControllerTest {

    @Autowired
    private NetworkRepo netRepo;

    /*private String getRootUrl() {
        return "http://localhost:4200/api/cameras";
        //return "http://sigma-argus.herokuapp.com/api/cameras";
    }*/

    @Test
    void getAllNetworks() {
        Network net1 = new Network("Network1", "+27833991336");
        netRepo.save(net1);
        Network net2 = new Network("Network2", "+27833991336");
        netRepo.save(net2);
        Network net3 = new Network("Network3", "+27833991336");
        netRepo.save(net3);
        List<Network> list = netRepo.findAll();
        assertTrue(list.size() > 0);
    }

    @Test
    void getNetworkById() {
        Network net = new Network("TestNetwork1", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        Optional<Network> findNetwork = netRepo.findById(savedNetwork.getNetworkId());
        assertNotNull(findNetwork);
        findNetwork.ifPresent(network -> assertEquals("TestNetwork1", network.getNetName()));
    }

    @Test
    void addNetwork() {
        Network net = new Network("TestNetwork2", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        assertNotNull(savedNetwork);
    }

    @Test
    void editNetwork() {
        Network net = new Network("TestNetwork3", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        assertEquals("TestNetwork3", savedNetwork.getNetName());
        savedNetwork.setNetName("UpdatedNetwork");
        savedNetwork = netRepo.save(savedNetwork);
        assertEquals("UpdatedNetwork", savedNetwork.getNetName());
    }

    @Test
    void deleteNetwork() {
        Network net = new Network("TestNetwork4", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        boolean existsBeforeDelete = netRepo.findById(savedNetwork.getNetworkId()).isPresent();
        assertTrue(existsBeforeDelete);
        netRepo.deleteById(savedNetwork.getNetworkId());
        boolean existsAfterDelete = netRepo.findById(savedNetwork.getNetworkId()).isPresent();
        assertFalse(existsAfterDelete);
    }
}
