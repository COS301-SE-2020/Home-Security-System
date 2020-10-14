package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.model.Network;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.repository.NetworkRepo;
import com.springboot.SpringBackend.repository.PersonRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonControllerTest {
    @Autowired
    private NetworkRepo netRepo;

    @Autowired
    private PersonRepo psnRepo;

    /*private String getRootUrl() {
        return "http://localhost:4200/api/cameras";
        //return "http://sigma-argus.herokuapp.com/api/cameras";
    }*/

    @Test
    void getPeopleList() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        Person psn1 = new Person("Image1",savedNetwork);
        psnRepo.save(psn1);
        Person psn2 = new Person("Image2",savedNetwork);
        psnRepo.save(psn2);
        List<Person> list = psnRepo.findAll();
        assertTrue(list.size() > 0);
    }

    @Test
    void getPersonById() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        Person psn = new Person("SomeImage", savedNetwork);
        Person saved = psnRepo.save(psn);
        Optional<Person> find = psnRepo.findById(saved.getPersonId());
        assertNotNull(find);
        find.ifPresent(person -> {
            assertEquals("SomeImage", person.getPersonImg());
            assertEquals("Unknown", person.getFname());
            assertEquals("Unknown", person.getLname());
            assertEquals("Grey", person.getPersonListed());
            assertEquals("TestNetwork", person.getNetwork().getNetName());
        });
    }

    @Test
    void addPerson() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        Person psn = new Person("SomeImage", savedNetwork);
        Person saved = psnRepo.save(psn);
        assertNotNull(saved);
    }

    @Test
    void editPerson() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        Person psn = new Person("SomeImage", savedNetwork);
        Person saved = psnRepo.save(psn);
        assertEquals("Unknown", saved.getFname());
        saved.setFname("Brad");
        saved = psnRepo.save(saved);
        assertEquals("Brad", saved.getFname());
    }

    @Test
    void deletePerson() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        Person psn = new Person("SomeImage","Message", savedNetwork);
        Person saved = psnRepo.save(psn);

        boolean existsBeforeDelete = psnRepo.findById(saved.getPersonId()).isPresent();
        assertTrue(existsBeforeDelete);
        psnRepo.deleteById(saved.getPersonId());
        boolean existsAfterDelete = psnRepo.findById(saved.getPersonId()).isPresent();
        assertFalse(existsAfterDelete);
    }
}
