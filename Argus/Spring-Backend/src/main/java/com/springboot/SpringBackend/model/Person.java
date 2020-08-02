package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person implements Serializable {
    private static final long serialVersionUID = -2126183802877200868L;

    /*public enum personType {
        White, Grey, Black;
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name="image_id", nullable = false)
    private Image personImg;
    @Column(name = "fname", nullable = true)
    private String fname = "";
    @Column(name = "lname", nullable = true)
    private String lname = "";
    //@Enumerated(EnumType.STRING)
    @Column(name = "personlisted", nullable = false)
    //private personType personListed;
    private String personListed;
    @Column(name = "personcreated", nullable = false)
    private LocalDate personCreated;
    @Column(name = "persondeleted", nullable = true)
    private LocalDate personDeleted = null;
    @OneToMany(mappedBy="person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @BatchSize(size = 1000)
    @JsonIgnore
    private List<Vehicle> vehicleList = new ArrayList<>();

    /*
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PersonVehicle",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Vehicle> vehicleList = new ArrayList<>();
    */

    public Person() { }

    public Person(Image img, String listed) {
        this.personImg = img;
        this.fname = "";
        this.lname = "";

        if(listed.equalsIgnoreCase("White"))
        {
            this.personListed = listed;
            //this.personListed = personType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            this.personListed = listed;
            //this.personListed = personType.Black;
        }
        else
        {
            this.personListed = listed;
            //this.personListed = personType.Grey;
        }

        this.personCreated = LocalDate.now();
    }

    public Person(Image img, String name, String surname, String listed) {
        this.personImg = img;
        this.fname = name;
        this.lname = surname;

        if(listed.equalsIgnoreCase("White"))
        {
            this.personListed = listed;
            //this.personListed = personType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            this.personListed = listed;
            //this.personListed = personType.Black;
        }
        else
        {
            this.personListed = listed;
            //this.personListed = personType.Grey;
        }

        this.personCreated = LocalDate.now();
    }

    public Person(Image img, String name, String surname) {
        this.personImg = img;
        this.fname = name;
        this.lname = surname;
        this.personListed = "Grey";
        //this.personListed = personType.Grey;
        this.personCreated = LocalDate.now();
    }

    public Long getPersonId() {
        return this.id;
    }
    public void setPersonId(Long id) {
        this.id = id;
    }

    public Long getImageId() { return this.personImg.getImageId(); }
    public Image getPersonImg() { return this.personImg; }
    public void setPersonImg(Image img) {
        if (img != null) {
            this.personImg = img;
        }
    }

    public String getFname() {
        return this.fname;
    }
    public void setFname(String name) {
        this.fname = name;
    }

    public String getLname() {
        return this.lname;
    }
    public void setLname(String name) {
        this.lname = name;
    }

    public String getPersonListed() { return this.personListed.toString(); }
    public void setPersonListed(String listed) {
        if(listed.equalsIgnoreCase("White"))
        {
            this.personListed = listed;
            //this.personListed = personType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            this.personListed = listed;
            //this.personListed = personType.Black;
        }
        else
        {
            this.personListed = listed;
            //this.personListed = personType.Grey;
        }
    }

    public LocalDate getPersonCreated() {
        return this.personCreated;
    }
    public void setPersonCreated(LocalDate date) { this.personCreated = date; }

    public LocalDate getPersonDeleted() {
        if(personDeleted != null) {
            return this.personDeleted;
        }
        return null;
    }
    public void setPersonDeleted() { this.personDeleted = LocalDate.now(); }

    public List<Vehicle> getVehicleList() { return this.vehicleList; }
    public void setVehicleList(List<Vehicle> list) {this.vehicleList = list;}
}
