package com.springboot.SpringBackend.dao;

import com.springboot.SpringBackend.model.PersonVehicle;
import com.springboot.SpringBackend.model.Vehicle;
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

//@Repository("VehicleDAO")
public class VehicleDAOImpl //extends JdbcDaoSupport implements VehicleDAO
{
    /*
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public List<Vehicle> findAllMotors() {
        String sql = "SELECT * FROM vehicle_Table";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Vehicle> result = new ArrayList<Vehicle>();
        for(Map<String, Object> row:rows){
            Vehicle x = new Vehicle();
            //x.((Long)row.get("person_id"));
            //x.((Long)row.get("vehicle_id"));
            result.add(x);
        }

        return result;
    }

    @Override
    public Vehicle getMotorById(Long id) {
        return null;
    }

    @Override
    public Vehicle createMotor(Vehicle x) {

    }

    @Override
    public Vehicle updateMotor(Vehicle x) {

    }

    @Override
    public void deleteMotor(Vehicle x) {

    }

    @Override
    public void deleteMotorById(Long id) {

    }

     */
}
