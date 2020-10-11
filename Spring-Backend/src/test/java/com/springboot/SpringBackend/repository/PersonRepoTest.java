package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.Network;
import com.springboot.SpringBackend.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepoTest {
    @Autowired
    private PersonRepo psnRepo;

    @Autowired
    private NetworkRepo netRepo;

    @Test
    public void testPersistence() {
        Network net = new Network("RaspberryPi4");
        netRepo.save(net);

        Person psn = new Person("TestImage",net);
        psnRepo.save(psn);

        assertNotNull(psn.getPersonId());
        Person newPsn = psnRepo.findById(psn.getPersonId()).orElse(null);
        assert newPsn != null;
        assertEquals("TestImage", newPsn.getPersonImg());
        assertEquals("Unknown", newPsn.getFname());
        assertEquals("Unknown", newPsn.getLname());
        assertEquals("Grey", newPsn.getPersonListed());
        assertEquals("RaspberryPi4", newPsn.getNetwork().getNetName());
    }
}
