package com.springboot.SpringBackend.dao;

import com.springboot.SpringBackend.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationDAO {
    List<Notification> findAllEvents();

    Notification getEventById(Long id);

    Notification createEvent(Notification x);

    Notification updateEvent(Notification x);

    void deleteAllEvents();

    void deleteEvent(Notification x);

    void deleteEventById(Long id);
}
