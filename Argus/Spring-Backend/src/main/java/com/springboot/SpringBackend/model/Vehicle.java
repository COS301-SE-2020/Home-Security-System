package com.springboot.SpringBackend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Person.class)*/
@Table(name = "vehicle")
public class Vehicle {
    public enum vehicleType implements Serializable {
        White, Grey, Black;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_id", nullable = false)
    private Long id;
    //@Column(name = "image_id", nullable = false)
    @OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "image_id")
    private Image vehicleImg;
    @Enumerated(EnumType.STRING)
    @Column(name = "vehiclelisted", nullable = false)
    private vehicleType vehicleListed;
    @Column(name = "licenseno", nullable = false)
    private String licenseNo;
    @Column(name = "vehiclecreated", nullable = false)
    private LocalDate vehicleCreated;
    @Column(name = "vehicledeleted", nullable = true)
    private LocalDate vehicleDeleted;

    @ManyToOne
    @JoinColumn(name="person_id", nullable = false)
    private Person person;

    //@ManyToMany(mappedBy = "vehicleList")
    //private List<Person> personList = new ArrayList<>();

    public Vehicle() { }

    public Vehicle( Image img, String listed, String licenseNo) {
        this.vehicleImg = img;

        if(listed.equalsIgnoreCase("White"))
        {
            this.vehicleListed = vehicleType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            this.vehicleListed = vehicleType.Black;
        }
        else
        {
            this.vehicleListed = vehicleType.Grey;
        }

        this.licenseNo = licenseNo;
        this.vehicleCreated = LocalDate.now();
    }

    public Vehicle(Image img, String licenseNo) {
        this.vehicleImg = img;
        this.vehicleListed = vehicleType.Grey;
        this.licenseNo = licenseNo;
        this.vehicleCreated = LocalDate.now();
    }

    public Long getVehicleId() {
        return this.id;
    }
    public void setVehicleId(Long id) {
        this.id = id;
    }

    public Image getVehicleImg() {
        return this.vehicleImg;
    }
    public void setVehicleImg(Image img) {
        if (img != null) {
            this.vehicleImg = img;
        }
    }

    public String getVehicleListed() {
        return this.vehicleListed.toString();
    }
    public void setVehicleListed(String listed) {
        if(listed.equalsIgnoreCase("White"))
        {
            this.vehicleListed = vehicleType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            this.vehicleListed = vehicleType.Black;
        }
        else
        {
            this.vehicleListed = vehicleType.Grey;
        }
    }

    public String getLicenseNo() {
        return this.licenseNo;
    }
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public LocalDate getVehicleCreated() {
        return this.vehicleCreated;
    }
    public void setVehicleCreated(LocalDate date) { this.vehicleCreated = date; }

    public LocalDate getVehicleDeleted() {
        return this.vehicleDeleted;
    }
    public void setVehicleDeleted() {
        this.vehicleDeleted = LocalDate.now();
    }

    public Person getPerson() { return this.person; }
    public void setPerson(Person x) { this.person = x; }
}
