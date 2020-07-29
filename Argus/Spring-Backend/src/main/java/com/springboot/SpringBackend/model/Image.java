package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Person.class)*/
@JsonIgnoreProperties(
        value = {"imageDeleted"},
        allowGetters = true,
        allowSetters = true
)
@Table(name = "Image")
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id", nullable = false)
    private Long id;
    @Column(name = "photo", nullable = false)
    private String photo;
    @Column(name = "imagedeleted", nullable = true)
    private LocalDate imageDeleted;

    @OneToOne(mappedBy = "profilePhoto", fetch = FetchType.LAZY)
    private Users user;
    @OneToOne(mappedBy = "personImg", fetch = FetchType.LAZY)
    private Person person;
    @OneToOne(mappedBy = "vehicleImg", fetch = FetchType.LAZY)
    private Vehicle vehicle;
    @OneToOne(mappedBy = "notificationImg", fetch = FetchType.LAZY)
    private Notification notification;

    public Image() { }
    public Image(String img) {
        this.photo = img;
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
        this.photo = img;
    }

    public LocalDate getImageDeleted() {
        return this.imageDeleted;
    }
    public void setImageDeleted() {
        this.imageDeleted = LocalDate.now();
    }
}
