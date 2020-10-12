package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.Network;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NetworkRepoTest {

    @Autowired
    private NetworkRepo netRepo;

    @Test
    public void testPersistence() {
        Network net = new Network("RaspberryPi4");
        netRepo.save(net);

        assertNotNull(net.getNetworkId());
        Network newNet = netRepo.findById(net.getNetworkId()).orElse(null);
        assert newNet != null;
        assertEquals("RaspberryPi4", newNet.getNetName());
    }
}
