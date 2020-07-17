package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.dto.VehicleDTO;
import com.springboot.SpringBackend.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<Vehicle> listAllVehicles();

    Optional<Vehicle> getVehicleByID(Long id);

    Vehicle createVehicle(Vehicle x);

    //Vehicle createVehicleForm(VehicleDTO dto);

    Vehicle updateVehicle(Vehicle x);

    //Vehicle updateVehicleForm(VehicleDTO dto);

    void deleteVehicle(Vehicle x);

    void deleteVehicleById(Long id);
}
