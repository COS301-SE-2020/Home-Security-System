package com.springboot.SpringBackend.dao;

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

//@Repository("PersonVehicleDAO")
public class PersonVehicleDAOImpl //extends JdbcDaoSupport implements PersonVehicleDAO
{
    /*
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public List<PersonVehicle> findAllPV() {
        String sql = "SELECT * FROM personvehicle_Table";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<PersonVehicle> result = new ArrayList<PersonVehicle>();
        for(Map<String, Object> row:rows){
            PersonVehicle x = new PersonVehicle();
            //x.((Long)row.get("person_id"));
            //x.((Long)row.get("vehicle_id"));
            result.add(x);
        }

        return result;
    }
    */
}
