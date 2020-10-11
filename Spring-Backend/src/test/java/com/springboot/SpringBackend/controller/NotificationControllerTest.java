package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.model.Network;
import com.springboot.SpringBackend.model.Notification;
import com.springboot.SpringBackend.repository.NetworkRepo;
import com.springboot.SpringBackend.repository.NotificationRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NotificationControllerTest {

    @Autowired
    private NotificationRepo noteRepo;

    @Autowired
    private NetworkRepo netRepo;

    @Test
    void getAllNotifications() {
        Network net = new Network("Network1");
        Network savedNetwork = netRepo.save(net);
        Notification note1 = new Notification("Image1","Message1",savedNetwork);
        noteRepo.save(note1);
        Notification note2 = new Notification("Image1","Message2",savedNetwork);
        noteRepo.save(note2);
        List<Notification> list = noteRepo.findAll();
        assertTrue(list.size() > 0);
    }

    @Test
    void getNotificationById() {
        Network net = new Network("TestNetwork1");
        Network savedNetwork = netRepo.save(net);
        Notification note = new Notification("SomeImage","SomeMessage", savedNetwork);
        Notification savedNote = noteRepo.save(note);
        Optional<Notification> findNote = noteRepo.findById(savedNote.getNotificationId());
        assertNotNull(findNote);
        if(findNote.isPresent()) {
            assertEquals("SomeMessage", findNote.get().getMessage());
            assertEquals("TestNetwork1", findNote.get().getNetwork().getNetName());
        }
    }

    @Test
    void addNotification() {
        Network net = new Network("RaspberyPi4");
        Network savedNetwork = netRepo.save(net);
        Notification note = new Notification("SomeImage" ,"SomeMessage", savedNetwork);
        Notification savedNote = noteRepo.save(note);
        assertNotNull(savedNote);
    }

    @Test
    void editNotification() {
        Network net = new Network("TestNetwork3");
        Network savedNetwork = netRepo.save(net);
        Notification note = new Notification("SomeImage","SomeMessage", savedNetwork);
        Notification savedNote = noteRepo.save(note);
        assertEquals("SomeMessage", savedNote.getMessage());
        savedNote.setMessage("UpdatedMessage");
        savedNote = noteRepo.save(savedNote);
        assertEquals("UpdatedMessage", savedNote.getMessage());
    }

    @Test
    void deleteNotification() {
        Network net = new Network("TestNetwork4");
        Network savedNetwork = netRepo.save(net);
        Notification cam = new Notification("SomeImage","SomeMessage", savedNetwork);
        Notification savedNote = noteRepo.save(cam);

        boolean existsBeforeDelete = noteRepo.findById(savedNote.getNotificationId()).isPresent();
        assertTrue(existsBeforeDelete);
        noteRepo.deleteById(savedNote.getNotificationId());
        boolean existsAfterDelete = noteRepo.findById(savedNote.getNotificationId()).isPresent();
        assertFalse(existsAfterDelete);
    }
}
