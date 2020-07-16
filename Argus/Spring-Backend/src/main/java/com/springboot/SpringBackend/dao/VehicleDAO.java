package com.springboot.SpringBackend.dao;

import com.springboot.SpringBackend.model.Vehicle;

import java.util.List;

public interface VehicleDAO {
    List<Vehicle> findAllMotors();

    Vehicle getMotorById(Long id);

    Vehicle createMotor(Vehicle x);

    Vehicle updateMotor(Vehicle x);

    void deleteMotor(Vehicle x);

    void deleteMotorById(Long id);
}
