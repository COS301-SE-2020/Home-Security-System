package com.springboot.SpringBackend.dto;

import com.springboot.SpringBackend.model.Notification;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.model.User;
import com.springboot.SpringBackend.model.Vehicle;

import java.io.Serializable;

public class ImageDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String image;
    private String deletionDate = "";

    /*
    private User user;
    private Person person;
    private Vehicle vehicle;
    private Notification notification;
    */

    public ImageDTO() { }
    public ImageDTO(String img) {
        this.image = img;
    }

    public Long getImageId() {
        return this.id;
    }
    public void setImageId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return this.image;
    }
    public void setImage(String img) {
        this.image = img;
    }

    public String getDeletionDate() {
        return this.deletionDate;
    }
    public void setDeletionDate(String date) {
        this.deletionDate = date;
    }

    @Override
    public String toString() {
        return "Image [photo_ID=" + id + ", image=" + image + ", deletionDate=" + deletionDate + "]";
    }
}
