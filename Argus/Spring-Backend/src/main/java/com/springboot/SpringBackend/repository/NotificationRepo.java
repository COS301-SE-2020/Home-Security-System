package com.springboot.SpringBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.SpringBackend.model.Notification;

import java.util.List;

@Repository
//public interface NotificationRepo extends CrudRepository<Notification, Long>{ }
public interface NotificationRepo extends JpaRepository<Notification, Long>{
    //List<Notification> findNotificationByDate(String date);
}
