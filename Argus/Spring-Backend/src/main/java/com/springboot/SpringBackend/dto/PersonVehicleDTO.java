package com.springboot.SpringBackend.dto;

import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.model.PersonVehicleKey;
import com.springboot.SpringBackend.model.Vehicle;

import java.io.Serializable;

public class PersonVehicleDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    public PersonVehicleKey id;
    private Person personvehicle;
    private Vehicle vehicleperson;
    private String deletionDate;

    public String getDeletionDate() {
        return this.deletionDate;
    }
    public void setDeletionDate(String date) {
        this.deletionDate = date;
    }

    @Override
    public String toString() {
        return "PersonVehicle [personvehicle_id=" + id + ", person_id=" + personvehicle.getPersonId() +
                ", Vehicle_id=" + vehicleperson.getVehicleId() + ", deletionDate=" + deletionDate + "]";
    }
}
