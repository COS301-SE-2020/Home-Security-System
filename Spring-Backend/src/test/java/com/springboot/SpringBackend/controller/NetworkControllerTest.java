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
        Network net1 = new Network("NetworkOne", "+27833991336");
        netRepo.save(net1);
        Network net2 = new Network("NetworkTwo", "+27833991336");
        netRepo.save(net2);
        Network net3 = new Network("NetworkThree", "+27833991336");
        netRepo.save(net3);
        List<Network> list = netRepo.findAll();
        assertTrue(list.size() > 0);
    }

    @Test
    void getNetworkById() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        Optional<Network> findNetwork = netRepo.findById(savedNetwork.getNetworkId());
        assertNotNull(findNetwork);
        findNetwork.ifPresent(network -> assertEquals("TestNetwork", network.getNetName()));
    }

    @Test
    void addNetwork() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        assertNotNull(savedNetwork);
    }

    @Test
    void editNetwork() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        assertEquals("TestNetwork", savedNetwork.getNetName());
        savedNetwork.setNetName("Updated");
        savedNetwork = netRepo.save(savedNetwork);
        assertEquals("Updated", savedNetwork.getNetName());
    }

    @Test
    void deleteNetwork() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        boolean existsBeforeDelete = netRepo.findById(savedNetwork.getNetworkId()).isPresent();
        assertTrue(existsBeforeDelete);
        netRepo.deleteById(savedNetwork.getNetworkId());
        boolean existsAfterDelete = netRepo.findById(savedNetwork.getNetworkId()).isPresent();
        assertFalse(existsAfterDelete);
    }
}
