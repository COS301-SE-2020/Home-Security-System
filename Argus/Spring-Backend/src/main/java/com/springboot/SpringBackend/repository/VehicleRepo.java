package com.springboot.SpringBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.SpringBackend.model.Vehicle;

@Repository
//public interface VehicleRepo extends CrudRepository<Vehicle, Long>{ }
public interface VehicleRepo extends JpaRepository<Vehicle, Long>{ }