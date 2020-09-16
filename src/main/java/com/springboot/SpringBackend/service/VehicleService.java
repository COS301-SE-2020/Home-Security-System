package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<Vehicle> getAllVehicles();

    Optional<Vehicle> getVehicleById(Long id);

    Vehicle createVehicle(Vehicle x);

    Vehicle updateVehicle(Vehicle x);

    void deleteVehicle(Vehicle x);

    void deleteAllVehicles();
}
