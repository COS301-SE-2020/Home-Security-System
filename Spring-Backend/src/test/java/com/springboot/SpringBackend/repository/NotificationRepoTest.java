package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.Network;
import com.springboot.SpringBackend.model.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        Network net = new Network("RaspberryPi4");
        netRepo.save(net);

        Notification note = new Notification("TestImage","TestMessage",net);
        noteRepo.save(note);

        assertNotNull(note.getNotificationId());
        Notification newNote = noteRepo.findById(note.getNotificationId()).orElse(null);
        assert newNote != null;
        assertEquals("TestImage", newNote.getNotificationImg());
        assertEquals("TestMessage", newNote.getMessage());
        assertEquals("Suspicious", newNote.getListed());
        assertEquals("RaspberryPi4", newNote.getNetwork().getNetName());
    }
}
