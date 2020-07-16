package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.PersonVehicle;
import com.springboot.SpringBackend.model.PersonVehicleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface PersonVehicleRepo extends CrudRepository<PersonVehicle, PersonVehicleKey> { }
public interface PersonVehicleRepo extends JpaRepository<PersonVehicle, PersonVehicleKey> { }
