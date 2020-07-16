package com.springboot.SpringBackend.model;

import javax.persistence.*;
import java.io.Serializable;

import lombok.*;

@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Person.class)*/
@Table(name = "personvehicle_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonVehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    public PersonVehicleKey id;

    @MapsId(value = "personID")
    @ManyToOne
    private Person personvehicle;

    @MapsId(value = "vehicleID")
    @ManyToOne
    private Vehicle vehicleperson;

    @Column(name = "deletionDate", nullable = true)
    private String deletionDate = "";

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
