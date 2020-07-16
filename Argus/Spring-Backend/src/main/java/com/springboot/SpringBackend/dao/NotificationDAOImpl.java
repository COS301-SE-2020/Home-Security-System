package com.springboot.SpringBackend.dao;

import com.springboot.SpringBackend.model.Notification;
import com.springboot.SpringBackend.model.PersonVehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Repository("NotificationDAO")
public class NotificationDAOImpl //extends JdbcDaoSupport implements NotificationDAO
{
    /*
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public List<Notification> findAllEvents() {
        String sql = "SELECT * FROM notification_Table";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Notification> result = new ArrayList<Notification>();
        for(Map<String, Object> row:rows){
            Notification x = new Notification();
            //x.((Long)row.get("person_id"));
            //x.((Long)row.get("vehicle_id"));
            result.add(x);
        }

        return result;
    }

    @Override
    public Notification getEventById(Long id) {
        return null;
    }

    @Override
    public Notification createEvent(Notification x) {

    }

    @Override
    public Notification updateEvent(Notification x) {

    }

    @Override
    public void deleteAllEvents() {

    }

    @Override
    public void deleteEvent(Notification x) {

    }

    @Override
    public void deleteEventById(Long id) {

    }

     */
}
