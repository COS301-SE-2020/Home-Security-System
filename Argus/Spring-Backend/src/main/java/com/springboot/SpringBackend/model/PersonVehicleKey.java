package com.springboot.SpringBackend.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PersonVehicleKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "person_id", nullable = false)
    public Long personID;
    @Column(name = "vehicle_id", nullable = false)
    public Long vehicleID;
}
