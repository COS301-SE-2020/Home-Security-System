package com.springboot.SpringBackend.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Person.class)*/
@Table(name = "photo_table")
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "photoID_seq")
    //@SequenceGenerator(name = "photoID_seq", sequenceName = "photoID_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id", nullable = false)
    private Long id;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "deletionDate", nullable = true)
    private String deletionDate = "";


    @OneToOne(mappedBy = "profilePhoto", fetch = FetchType.LAZY)
    private User user;
    @OneToOne(mappedBy = "personImg", fetch = FetchType.LAZY)
    private Person person;
    @OneToOne(mappedBy = "vehicleImg", fetch = FetchType.LAZY)
    private Vehicle vehicle;
    @OneToOne(mappedBy = "notificationImg", fetch = FetchType.LAZY)
    private Notification notification;

    public Image() { }

    public Image(String img) {
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
