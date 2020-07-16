package com.springboot.SpringBackend.dto;

import com.springboot.SpringBackend.model.Image;
import com.springboot.SpringBackend.model.PersonVehicle;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PersonDTO implements Serializable {
    public enum personType {
        White, Grey, Black;
    }

    private static final long serialVersionUID = 1L;
    private Long id;
    private String fullname;
    private personType listed;
    private String created;
    private String deletionDate;

    private Image personImg;
    private List<PersonVehicle> vehicleList;

    public PersonDTO() { }

    public PersonDTO(String name, String listed, Image img) {
        this.fullname = name;

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

    public PersonDTO(String name, Image img) {
        this.fullname = name;
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

    public String getFullName() {
        return this.fullname;
    }
    public void setFullName(String name) {
        this.fullname = name;
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
    public void setImage(Image img) { this.personImg = img; }

    public String getDeletionDate() {
        return this.deletionDate;
    }
    public void setDeletionDate(String date) {
        this.deletionDate = date;
    }

    @Override
    public String toString() {
        return "Person [person_ID=" + id + ", fullname=" + fullname +
                ", listed=" + listed + ", created=" + created +
                ", photo_ID=" + personImg.getImageId() +
                ", deletionDate=" + deletionDate + "]";
    }
}
