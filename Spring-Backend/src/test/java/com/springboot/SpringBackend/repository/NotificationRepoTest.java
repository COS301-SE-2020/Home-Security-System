package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.Network;
import com.springboot.SpringBackend.model.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NotificationRepoTest {
    @Autowired
    private NotificationRepo noteRepo;

    @Autowired
    private NetworkRepo netRepo;

    @Test
    public void testPersistence() {
        Network net = new Network("RaspberryPi4", "+27833991336");
        netRepo.save(net);

        Notification note = new Notification("TestImage","TestMessage",net);
        noteRepo.save(note);

        assertNotNull(note.getNotificationId());
        Optional<Notification> newNote = noteRepo.findById(note.getNotificationId());
        newNote.ifPresent(notification -> {
            assertEquals("TestImage", notification.getNotificationImg());
            assertEquals("TestMessage", notification.getMessage());
            assertEquals("Suspicious", notification.getListed());
            assertEquals("RaspberryPi4", notification.getNetwork().getNetName());
        });
    }
}
