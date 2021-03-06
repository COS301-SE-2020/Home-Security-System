package com.springboot.SpringBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.SpringBackend.model.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long>{ }
