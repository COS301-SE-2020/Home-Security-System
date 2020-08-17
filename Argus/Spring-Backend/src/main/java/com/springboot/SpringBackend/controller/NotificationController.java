package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.Notification;
import com.springboot.SpringBackend.model.User;
import com.springboot.SpringBackend.repository.UserRepo;
import com.springboot.SpringBackend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {
    private final NotificationService service;
    private final UserRepo repo;

    @Autowired
    public NotificationController(NotificationService service, UserRepo repo) {
        this.repo = repo;
        this.service = service;
    }

    @GetMapping("/notifications")
    public List<Notification> getAllNotifications() {
       return service.getAllNotifications();
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Notification x = service.getNotificationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found for this id :: " + id));
        return ResponseEntity.ok().body(x);
    }

    @PostMapping("/notifications")
    public Notification addNotification(@Valid @RequestBody Notification x) {
        return service.createNotification(x);
    }

    @PutMapping("/notifications/{id}")
    public ResponseEntity<Notification> editNotification(@PathVariable(value = "id") Long id,
                                         @Valid @RequestBody Notification details) throws ResourceNotFoundException {
        Notification x = service.getNotificationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found for this id :: " + id));

        x.setNotificationId(details.getNotificationId());
        if(details.getNotificationImg() != null) {
            x.setNotificationImg(details.getNotificationImg());
        }
        x.setListed(details.getListed());
        x.setMessage(details.getMessage());
        if(details.getUser() != null) {
            x.setUser(details.getUser());
        }
        x.setNotificationDeleted(details.getNotificationDeleted());
        final Notification updatedNotification = service.updateNotification(x);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/notifications/{id}")
    public Map<String, Boolean> deleteNotification(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Notification x = service.getNotificationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found for this id :: " + id));

        service.deleteNotification(x);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
