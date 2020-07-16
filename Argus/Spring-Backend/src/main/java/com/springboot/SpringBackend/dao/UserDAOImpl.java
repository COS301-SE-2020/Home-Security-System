package com.springboot.SpringBackend.dao;

import com.springboot.SpringBackend.model.PersonVehicle;
import com.springboot.SpringBackend.model.User;
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

//@Repository("UserDAO")
public class UserDAOImpl //extends JdbcDaoSupport implements UserDAO
{
    /*
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM user_Table";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<User> result = new ArrayList<User>();
        for(Map<String, Object> row:rows){
            User x = new User();
            //x.((Long)row.get("person_id"));
            //x.((Long)row.get("vehicle_id"));
            result.add(x);
        }

        return result;
    }

    @Override
    public User getUsrById(Long id) {
        return null;
    }

    @Override
    public User createUsr(User x) {

    }

    @Override
    public User updateUsr(User x) {

    }

    @Override
    public void deleteUsr(User x) {

    }

    @Override
    public void deleteUsrById(Long id) {

    }

     */
}
