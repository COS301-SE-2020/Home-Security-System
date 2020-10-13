package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.User;
import com.springboot.SpringBackend.model.Network;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepoTest {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private NetworkRepo netRepo;

    @Test
    public void testPersistence() {
        Network net = new Network("RaspberryPi4");
        netRepo.save(net);

        User user = new User("TestImage","Brad","Zietsman","+27840763231",
                "brad.zietsman@gmail.com","Bradford","123qweASD!","Admin",
                "One","Peanut",net);
        userRepo.save(user);

        assertNotNull(user.getUserId());
        User newUser = userRepo.findById(user.getUserId()).orElse(null);
        assert newUser != null;
        assertEquals("TestImage", newUser.getProfilePhoto());
        assertEquals("Brad", newUser.getFname());
        assertEquals("Zietsman", newUser.getLname());
        assertEquals("+27840763231", newUser.getContactNo());
        assertEquals("brad.zietsman@gmail.com", newUser.getEmail());
        assertEquals("Bradford", newUser.getUsername());
        assertEquals("123qweASD!", newUser.getUserPass());
        assertEquals("Admin", newUser.getUserRole());
        assertEquals("One", newUser.getSecureQuestion());
        assertEquals("Peanut", newUser.getSecureAnswer());
        assertEquals("RaspberryPi4", newUser.getNetwork().getNetName());
    }
}
