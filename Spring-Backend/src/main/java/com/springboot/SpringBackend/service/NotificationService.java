package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<Notification> getAllNotifications();

    Optional<Notification> getNotificationById(Long id);

    Notification createNotification(Notification x);

    Notification updateNotification(Notification x);

    void deleteNotification(Notification x);

    void deleteAllNotifications();
}
