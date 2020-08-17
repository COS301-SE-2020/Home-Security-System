package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "image")
public class Image implements Serializable {
    private static final long serialVersionUID = -8366923365418680409L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id", nullable = false)
    private Long id;

    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "imagedeleted")
    private LocalDate imageDeleted = null;

    /*
    @JsonIgnore
    @OneToMany(mappedBy="profilePhoto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;
    @JsonIgnore
    @OneToMany(mappedBy="personImg", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Person> people;
    @JsonIgnore
    @OneToMany(mappedBy="vehicleImg", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;
    @JsonIgnore
    @OneToMany(mappedBy="notificationImg", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Notification> notifications = null;
    */

    public Image() { }
    public Image(String img) {
        this.photo = Jsoup.clean(img, Whitelist.simpleText());
    }

    public Long getImageId() {
        return this.id;
    }
    public void setImageId(Long id) {
        this.id = id;
    }

    public String getPhoto() {
        return this.photo;
    }
    public void setPhoto(String img) {
        this.photo = Jsoup.clean(img, Whitelist.simpleText());
    }

    public LocalDate getImageDeleted() {
        if(imageDeleted != null) {
            return this.imageDeleted;
        }
        return null;
    }
    public void setImageDeleted(LocalDate date) {
        if (date != null) {
            this.imageDeleted = LocalDate.now();
        } else {
            this.imageDeleted = null;
        }
    }
}
