package com.springboot.SpringBackend.dao;

import com.springboot.SpringBackend.model.Person;
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

//@Repository("PersonDAO")
public class PersonDAOImpl //extends JdbcDaoSupport implements PersonDAO
{
    /*
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public List<Person> findAllPeople() {
        String sql = "SELECT * FROM person_Table";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Person> result = new ArrayList<Person>();
        for(Map<String, Object> row:rows){
            Person x = new Person();
            //x.((Long)row.get("person_id"));
            //x.((Long)row.get("vehicle_id"));
            result.add(x);
        }

        return result;
    }

    @Override
    public Person getPeopleById(Long id) {
        return null;
    }

    @Override
    public Person createPeople(Person x) {

    }

    @Override
    public Person updatePeople(Person x) {

    }

    @Override
    public void deletePeople(Person x) {

    }

    @Override
    public void deletePeopleById(Long id) {

    }

     */
}
