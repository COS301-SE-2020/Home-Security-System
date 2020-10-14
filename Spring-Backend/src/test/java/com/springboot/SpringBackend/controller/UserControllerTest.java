package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.model.Network;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.model.User;
import com.springboot.SpringBackend.repository.NetworkRepo;
import com.springboot.SpringBackend.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserControllerTest {

    @Autowired
    private NetworkRepo netRepo;

    @Autowired
    private UserRepo userRepo;

    /*private String getRootUrl() {
        return "http://localhost:4200/api/cameras";
        //return "http://sigma-argus.herokuapp.com/api/cameras";
    }*/

    @Test
    void getAllUsers() {
        Network net = new Network("Network1", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        User u1 = new User("Image1","Name1","Surname1","Contact1","Email1",
                "User1", "123qweASD!","Admin","One","Teddy",savedNetwork);
        userRepo.save(u1);
        User u2 = new User("Image2","Name2","Surname2","Contact2","Email2",
                "User2", "123qweASD!","Admin","One","Teddy",savedNetwork);
        userRepo.save(u2);
        List<User> list = userRepo.findAll();
        assertTrue(list.size() > 0);
    }

    @Test
    void getUserById() {
        Network net = new Network("TestNetwork1", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        User u = new User("SomeImage","SomeName","SomeSurname","SomeContact","SomeEmail",
                "SomeUser", "123qweASD!","Admin","One","Teddy",savedNetwork);
        User saved = userRepo.save(u);
        Optional<User> find = userRepo.findById(saved.getUserId());
        assertNotNull(find);
        find.ifPresent(user -> {
            assertEquals("SomeImage", user.getProfilePhoto());
            assertEquals("SomeName", user.getFname());
            assertEquals("SomeSurname", user.getLname());
            assertEquals("SomeContact", user.getContactNo());
            assertEquals("SomeEmail", user.getEmail());
            assertEquals("SomeUser", user.getUsername());
            assertEquals("123qweASD!", user.getUserPass());
            assertEquals("Admin", user.getUserRole());
            assertEquals("TestNetwork1", user.getNetwork().getNetName());
        });
    }

    @Test
    void addUser() {
        Network net = new Network("RaspberyPi4", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        User u = new User("SomeImage","SomeName","SomeSurname","SomeContact","SomeEmail",
                "SomeUser", "123qweASD!","Admin","One","Teddy",savedNetwork);
        User saved = userRepo.save(u);
        assertNotNull(saved);
    }

    @Test
    void editUser() {
        Network net = new Network("TestNetwork3", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        User u = new User("SomeImage","SomeName","SomeSurname","SomeContact","SomeEmail",
                "SomeUser", "123qweASD!","Admin","One","Teddy",savedNetwork);
        User saved = userRepo.save(u);
        assertEquals("SomeName", saved.getFname());
        saved.setFname("Brad");
        saved = userRepo.save(saved);
        assertEquals("Brad", saved.getFname());
    }

    @Test
    void deleteUser() {
        Network net = new Network("TestNetwork4", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        User u = new User("SomeImage","SomeName","SomeSurname","SomeContact","SomeEmail",
                "SomeUser", "123qweASD!","Admin","One","Teddy",savedNetwork);
        User saved = userRepo.save(u);

        boolean existsBeforeDelete = userRepo.findById(saved.getUserId()).isPresent();
        assertTrue(existsBeforeDelete);
        userRepo.deleteById(saved.getUserId());
        boolean existsAfterDelete = userRepo.findById(saved.getUserId()).isPresent();
        assertFalse(existsAfterDelete);
    }
}
