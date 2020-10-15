package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.User;
import com.springboot.SpringBackend.model.Network;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

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
        Network net = new Network("TestNetwork", "+27833991336");
        netRepo.save(net);

        User user = new User("TestImage", "Brad", "Zietsman", "+27840763231",
                "brad.zietsman@gmail.com", "Bradford", "123qweASD!", "Admin",
                "One", "Peanut", net);
        userRepo.save(user);

        assertNotNull(user.getUserId());
        Optional<User> newUser = userRepo.findById(user.getUserId());
        newUser.ifPresent(value -> {
            assertEquals("TestImage", newUser.get().getProfilePhoto());
            assertEquals("Brad", newUser.get().getFname());
            assertEquals("Zietsman", newUser.get().getLname());
            assertEquals("+27840763231", newUser.get().getContactNo());
            assertEquals("brad.zietsman@gmail.com", newUser.get().getEmail());
            assertEquals("Bradford", newUser.get().getUsername());
            assertEquals("123qweASD!", newUser.get().getUserPass());
            assertEquals("Admin", newUser.get().getUserRole());
            assertEquals("One", newUser.get().getSecureQuestion());
            assertEquals("Peanut", newUser.get().getSecureAnswer());
            assertEquals("TestNetwork", newUser.get().getNetwork().getNetName());
        });
    }
}
