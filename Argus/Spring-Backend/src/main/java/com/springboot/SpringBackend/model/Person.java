package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person implements Serializable {
    private static final long serialVersionUID = -2126183802877200868L;

    public enum personType {
        White, Grey, Black;
    }

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

    @Enumerated(EnumType.STRING)
    @Column(name = "personlisted", nullable = false)
    private personType personListed;
    //private String personListed;

    @Column(name = "personcreated", nullable = false)
    private LocalDate personCreated;

    @Column(name = "persondeleted", nullable = true)
    private LocalDate personDeleted = null;

    @OneToMany(mappedBy="person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @BatchSize(size = 1000)
    @JsonIgnore
    private List<Vehicle> vehicleList = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy="person")
    private Face face;

    /*
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PersonVehicle",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Vehicle> vehicleList = new ArrayList<>();
    */

    public Person() { }

    public Person(Image img) {
        this.personImg = img;
        this.fname = "Unknown";
        this.lname = "Unknown";
        this.personListed = personType.Grey;
        this.personCreated = LocalDate.now();
    }

    public Person(Image img, String listed) {
        this.personImg = img;
        this.fname = "Unknown";
        this.lname = "Unknown";

        if(listed.equalsIgnoreCase("White"))
        {
            //this.personListed = "White";
            this.personListed = personType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            //this.personListed = "Black";
            this.personListed = personType.Black;
        }
        else
        {
            //this.personListed = "Grey";
            this.personListed = personType.Grey;
        }

        this.personCreated = LocalDate.now();
    }

    public Person(Image img, String name, String surname, String listed) {
        this.personImg = img;
        this.fname = Jsoup.clean(name, Whitelist.simpleText());
        this.lname = Jsoup.clean(surname, Whitelist.simpleText());

        if(listed.equalsIgnoreCase("White"))
        {
            //this.personListed = "White";
            this.personListed = personType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            //this.personListed = "Black";
            this.personListed = personType.Black;
        }
        else
        {
            //this.personListed = "Grey";
            this.personListed = personType.Grey;
        }

        this.personCreated = LocalDate.now();
    }

    public Person(Image img, String name, String surname) {
        this.personImg = img;
        this.fname = Jsoup.clean(name, Whitelist.simpleText());
        this.lname = Jsoup.clean(surname, Whitelist.simpleText());
        //this.personListed = "Grey";
        this.personListed = personType.Grey;
        this.personCreated = LocalDate.now();
    }

    public Long getPersonId() {
        return this.id;
    }
    public void setPersonId(Long id) {
        this.id = id;
    }

    public Long getPersonImgId() { return this.personImg.getImageId(); }
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
        this.fname = Jsoup.clean(name, Whitelist.simpleText());
    }

    public String getLname() {
        return this.lname;
    }
    public void setLname(String name) {
        this.lname = Jsoup.clean(name, Whitelist.simpleText());
    }

    public String getPersonListed() { return this.personListed.toString(); }
    public void setPersonListed(String listed) {
        if(listed.equalsIgnoreCase("White"))
        {
            //this.personListed = "White";
            this.personListed = personType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            //this.personListed = "Black";
            this.personListed = personType.Black;
        }
        else
        {
            //this.personListed = "Grey";
            this.personListed = personType.Grey;
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
    public void setPersonDeleted(LocalDate date) {
        if (date != null) {
            this.personDeleted = LocalDate.now();
        } else {
            this.personDeleted = null;
        }
    }

    public List<Vehicle> getVehicleList() { return this.vehicleList; }
    public void setVehicleList(List<Vehicle> list) {this.vehicleList = list; }
}
