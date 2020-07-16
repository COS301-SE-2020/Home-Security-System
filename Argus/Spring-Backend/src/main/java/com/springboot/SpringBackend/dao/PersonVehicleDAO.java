package com.springboot.SpringBackend.dao;

import com.springboot.SpringBackend.model.PersonVehicle;

import java.util.List;

public interface PersonVehicleDAO {
    List<PersonVehicle> findAllPV();
}
