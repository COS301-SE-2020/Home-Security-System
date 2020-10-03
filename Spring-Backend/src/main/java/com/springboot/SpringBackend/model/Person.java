package com.springboot.SpringBackend.model;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "person")
public class Person implements Serializable {
    private static final long serialVersionUID = -2126183802877200868L;

    public enum personType {
        White, Grey, Black
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id", nullable = false)
    private Long id;

    @Column(name = "image", nullable = false)
    private String personImg;

    @Column(name = "fname", nullable = false)
    private String fname = "";

    @Column(name = "lname", nullable = false)
    private String lname = "";

    @Enumerated(EnumType.STRING)
    @Column(name = "personlisted", nullable = false)
    private personType personListed;
    //private String personListed;

    @Column(name = "personcreated", nullable = false)
    private LocalDate personCreated;

    @Column(name = "persondeleted")
    private LocalDate personDeleted = null;

    @ManyToOne
    @JoinColumn(name="network_id", nullable = false)
    private Network network;

    public Person() { }

    public Person(String img, Network n) {
        this.personImg = img;
        this.fname = "Unknown";
        this.lname = "Unknown";
        this.personListed = personType.Grey;
        this.personCreated = LocalDate.now();
        this.network = n;
    }

    public Person(String img, String listed, Network n) {
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
        this.network = n;
    }

    public Person(String img, String name, String surname, String listed, Network n) {
        this.personImg = img;
        this.fname = Jsoup.clean(name, Whitelist.simpleText());
        this.lname = Jsoup.clean(surname, Whitelist.simpleText());
        this.network = n;

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

    public Person(String img, String name, String surname, Network n) {
        this.personImg = img;
        this.fname = Jsoup.clean(name, Whitelist.simpleText());
        this.lname = Jsoup.clean(surname, Whitelist.simpleText());
        //this.personListed = "Grey";
        this.personListed = personType.Grey;
        this.personCreated = LocalDate.now();
        this.network = n;
    }

    public Long getPersonId() {
        return this.id;
    }
    public void setPersonId(Long id) {
        this.id = id;
    }

    // public Long getPersonImgId() { return this.personImg.getImageId(); }
    public String getPersonImg() { return this.personImg; }
    public void setPersonImg(String img) {
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

    public Long getNetworkId() { return this.network.getNetworkId(); }
    public Network getNetwork() { return this.network; }
    public void setNetwork(Network x) { this.network = x; }
}
