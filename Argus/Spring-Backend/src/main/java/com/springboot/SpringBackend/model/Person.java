package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Person.class)*/
@Table(name = "person_table")
public class Person implements Serializable {
    public enum personType {
        White, Grey, Black;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id", nullable = false)
    private Long id;

    @Column(name = "fname", nullable = false)
    private String fname;

    @Column(name = "lname", nullable = false)
    private String lname;

    @Column(name = "listed", nullable = false)
    private personType listed;

    @Column(name = "created", nullable = false)
    private String created;

    @Column(name = "deletionDate", nullable = true)
    private String deletionDate = "";

    //@Column(name = "photo_id", nullable = false)
    @OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "photo_id")
    private Image personImg;

    //@ManyToMany(cascade = CascadeType.ALL)
    //@JoinTable(name = "personvehicle_Table",
    //        joinColumns = { @JoinColumn(name = "person_id", referencedColumnName = "person_id") },
    //        inverseJoinColumns = { @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id") })
    @OneToMany(mappedBy = "personvehicle")
    private List<PersonVehicle> vehicleList;

    public Person() { }

    public Person(String name, String surname, String listed, Image img) {
        this.fname = name;
        this.lname = surname;

        if(listed.equalsIgnoreCase("White"))
        {
            this.listed = personType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            this.listed = personType.Black;
        }
        else
        {
            this.listed = personType.Grey;
        }

        this.created = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.personImg = img;
    }

    public Person(String name, String surname, Image img) {
        this.fname = name;
        this.lname = surname;
        this.listed = personType.Grey;
        this.created = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.personImg = img;
    }

    public Long getPersonId() {
        return this.id;
    }
    public void setPersonId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.fname;
    }
    public void setName(String name) {
        this.fname = name;
    }

    public String getSurname() {
        return this.lname;
    }
    public void setSurname(String name) {
        this.lname = name;
    }

    public String getListed() { return this.listed.toString(); }
    public void setListed(String listed) {
        if(listed.equalsIgnoreCase("White"))
        {
            this.listed = personType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            this.listed = personType.Black;
        }
        else
        {
            this.listed = personType.Grey;
        }
    }

    public String getDate() {
        return this.created;
    }
    public void setDate(String date) {
        this.created = date;
    }

    public Long getImageById() { return this.personImg.getImageId(); }
    public Image getImage() { return this.personImg; }
    public void setImage(Image img) {
        if (img != null) {
            this.personImg = img;
        }
    }

    public String getDeletionDate() {
        return this.deletionDate;
    }
    public void setDeletionDate(String date) {
        this.deletionDate = date;
    }

    @Override
    public String toString() {
        return "Person [person_id=" + id + ", fname=" + fname +
                ", lname=" + lname + ", listed=" + listed + ", created=" + created +
                ", photo_id=" + personImg.getImageId() + ", deletionDate=" + deletionDate + "]";
    }
}
