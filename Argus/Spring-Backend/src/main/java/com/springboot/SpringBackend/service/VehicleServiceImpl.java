package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Vehicle;
import com.springboot.SpringBackend.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("VehicleService")
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepo repo;

    @Autowired
    public VehicleServiceImpl(VehicleRepo vRepo)
    {
        this.repo = vRepo;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return this.repo.findAll();
    }

    @Override
    public Optional<Vehicle> getVehicleById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Vehicle createVehicle(Vehicle x) {
        return this.repo.save(x);
    }

    @Override
    public Vehicle updateVehicle(Vehicle x) {
        return this.repo.save(x);
    }

    @Override
    public void deleteVehicle(Vehicle x) {
        this.repo.delete(x);
    }

    @Override
    public void deleteAllVehicles()
    {
        this.repo.deleteAll();
    }
}
