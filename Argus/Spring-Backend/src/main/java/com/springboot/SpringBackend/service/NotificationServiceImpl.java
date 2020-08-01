package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Notification;
import com.springboot.SpringBackend.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("NotificationService")
@Transactional
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepo repo;

    @Autowired
    public NotificationServiceImpl(NotificationRepo nRepo)
    {
        this.repo = nRepo;
    }

    @Override
    public List<Notification> getAllNotifications() {
        return (List<Notification>) this.repo.findAll();
    }

    @Override
    public Optional<Notification> getNotificationById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Notification createNotification(Notification x) { return this.repo.save(x); }

    @Override
    public Notification updateNotification(Notification x) { return this.repo.save(x); }

    @Override
    public void deleteNotification(Notification x) {
        this.repo.delete(x);
    }

    @Override
    public void deleteAllNotifications() {
        this.repo.deleteAll();
    }
}
