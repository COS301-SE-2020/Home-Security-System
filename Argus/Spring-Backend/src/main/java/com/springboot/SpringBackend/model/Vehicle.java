package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {
    private static final long serialVersionUID = -5448294771628276088L;

    /*public enum vehicleType {
        White, Grey, Black;
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name="image_id", nullable = false)
    private Image vehicleImg;
    //@Enumerated(EnumType.STRING)
    @Column(name = "vehiclelisted", nullable = false)
    //private vehicleType vehicleListed;
    private String vehicleListed;
    @Column(name = "licenseno", nullable = false)
    private String licenseNo;
    @Column(name = "vehiclecreated", nullable = false)
    private LocalDate vehicleCreated;
    @Column(name = "vehicledeleted", nullable = true)
    private LocalDate vehicleDeleted = null;
    @ManyToOne
    @JoinColumn(name="person_id", nullable = false)
    private Person person;

    //@ManyToMany(mappedBy = "vehicleList")
    //private List<Person> personList = new ArrayList<>();

    public Vehicle() { }

    public Vehicle(Image img, String listed, String licenseNo) {
        this.vehicleImg = img;

        if(listed.equalsIgnoreCase("White"))
        {
            this.vehicleListed = "White";
            //this.vehicleListed = vehicleType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            this.vehicleListed = "Black";
            //this.vehicleListed = vehicleType.Black;
        }
        else
        {
            this.vehicleListed = "Grey";
            //this.vehicleListed = vehicleType.Grey;
        }

        this.licenseNo = licenseNo;
        this.vehicleCreated = LocalDate.now();
    }

    public Vehicle(Image img, String licenseNo) {
        this.vehicleImg = img;
        this.vehicleListed = "Grey";
        //this.vehicleListed = vehicleType.Grey;
        this.licenseNo = licenseNo;
        this.vehicleCreated = LocalDate.now();
    }

    public Vehicle(Image img, String listed, String licenseNo, Person p) {
        this.vehicleImg = img;

        if(listed.equalsIgnoreCase("White"))
        {
            this.vehicleListed = "White";
            //this.vehicleListed = vehicleType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            this.vehicleListed = "Black";
            //this.vehicleListed = vehicleType.Black;
        }
        else
        {
            this.vehicleListed = "Grey";
            //this.vehicleListed = vehicleType.Grey;
        }

        this.licenseNo = licenseNo;
        this.vehicleCreated = LocalDate.now();
        this.person = p;
    }

    public Vehicle(Image img, String licenseNo, Person p) {
        this.vehicleImg = img;
        this.vehicleListed = "Grey";
        //this.vehicleListed = vehicleType.Grey;
        this.licenseNo = licenseNo;
        this.vehicleCreated = LocalDate.now();
        this.person = p;
    }

    public Long getVehicleId() {
        return this.id;
    }
    public void setVehicleId(Long id) {
        this.id = id;
    }

    public Long getVehicleImgId() { return this.vehicleImg.getImageId(); }
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
            this.vehicleListed = "White";
            //this.vehicleListed = vehicleType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            this.vehicleListed = "Black";
            //this.vehicleListed = vehicleType.Black;
        }
        else
        {
            this.vehicleListed = "Grey";
            //this.vehicleListed = vehicleType.Grey;
        }
    }

    public String getLicenseNo() {
        return this.licenseNo;
    }
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public LocalDate getVehicleCreated() { return this.vehicleCreated; }
    public void setVehicleCreated(LocalDate date) { this.vehicleCreated = date; }

    public LocalDate getVehicleDeleted() {
        if(vehicleDeleted != null) {
            return this.vehicleDeleted;
        }
        return null;
    }
    public void setVehicleDeleted(LocalDate date) {
        this.vehicleDeleted = date;
    }

    public Long getPersonId() { return this.person.getPersonId(); }
    public Person getPerson() { return this.person; }
    public void setPerson(Person x) { this.person = x; }
}
