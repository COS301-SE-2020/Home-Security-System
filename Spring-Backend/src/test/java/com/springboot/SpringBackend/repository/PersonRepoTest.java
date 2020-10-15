package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.Network;
import com.springboot.SpringBackend.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

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
        Network net = new Network("TestNetwork", "+27833991336");
        netRepo.save(net);

        Person psn = new Person("TestImage",net);
        psnRepo.save(psn);

        assertNotNull(psn.getPersonId());
        Optional<Person> newPsn= psnRepo.findById(psn.getPersonId());
        newPsn.ifPresent(person -> {
            assertEquals("TestImage", person.getPersonImg());
            assertEquals("Unknown", person.getFname());
            assertEquals("Unknown", person.getLname());
            assertEquals("Grey", person.getPersonListed());
            assertEquals("TestNetwork", person.getNetwork().getNetName());
        });
    }
}
