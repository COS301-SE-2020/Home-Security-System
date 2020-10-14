package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.Network;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NetworkRepoTest {

    @Autowired
    private NetworkRepo netRepo;

    @Test
    public void testPersistence() {
        Network net = new Network("RaspberryPi4", "+27833991336");
        netRepo.save(net);

        assertNotNull(net.getNetworkId());
        Optional<Network> newNet = netRepo.findById(net.getNetworkId());
        newNet.ifPresent(network -> assertEquals("RaspberryPi4", network.getNetName()));
    }
}
