package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.dao.NotificationDAOImpl;
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
    public List<Notification> listAllEvents() {
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
    public void deleteAllNotifications() {
        this.repo.deleteAll();
    }

    @Override
    public void deleteNotification(Notification x) {
        this.repo.delete(x);
    }

    @Override
    public void deleteNotificationById(Long id) {
        this.repo.deleteById(id);
    }

    /*
    private static NotificationDAO dao;

    @Autowired
    public NotificationService() {
		dao = new NotificationDAO();
	}

	public NotificationDAO getNotificationDao() {
		return dao;
	}

    @Override
    public List<Notification> listAllEvents() {
		List<Notification> list = dao.findAllEvents();
		return list;
	}

    @Override
    public Notification getNotificationById(Long id) {
		Notification x = dao.getEventById(id);
		return x;
	}

    @Override
    public Notification createNotification(Notification x) {
		dao.createEvent(x);
		return x;
	}

    @Override
    public Notification updateNotification(Notification x) {
		dao.updateEvent(x);
		return x;
	}

    @Override
    public void deleteNotification(Notification x) {
		dao.deleteEvent(x);
    }

    @Override
    public void deleteAllNotifications() {
        dao.deleteAllEvents();
    }

    @Override
    public void deleteNotificationById(Long id) {
		dao.deleteEventById(id);
	}
    */
}
