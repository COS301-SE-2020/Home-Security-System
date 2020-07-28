package com.springboot.SpringBackend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Person.class)*/
@Table(name = "notification")
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notification_id", nullable = false)
    private Long id;
    //@Column(name = "image_id", nullable = true)
    @OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "image_id")
    private Image notificationImg;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "ondate", nullable = false)
    private LocalDate onDate;
    @Column(name = "attime", nullable = false)
    private LocalTime atTime;
    @Column(name = "notificationdeleted", nullable = true)
    private LocalDate notificationDeleted;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private Users user;

    //@ManyToMany(mappedBy = "notificationList")
    //private List<Users> userList = new ArrayList<>();

    public Notification() {}

    public Notification(Image img, String msg) {
        this.notificationImg = img;
        this.message = msg;
        onDate = LocalDate.now();
        atTime = LocalTime.now();
    }

    public long getNotificationId() {
        return this.id;
    }
    public void setNotificationId(Long id) {
        this.id = id;
    }

    public Image getNotificationImg() { return this.notificationImg; }
    public void setNotificationImg(Image img) {
        if (img != null) {
            this.notificationImg = img;
        }
    }

    public String getMessage() {
        return this.message;
    }
    public void setMessage(String msg) {
        this.message = msg;
    }

    public LocalDate getOnDate() { return this.onDate; }
    public void setOnDate() { this.onDate = LocalDate.now(); }

    public LocalTime getAtTime() {
        return this.atTime;
    }
    public void setAtTime() { this.atTime = LocalTime.now();}

    public LocalDate getNotificationDeleted() {
        return this.notificationDeleted;
    }
    public void setNotificationDeleted() { this.notificationDeleted = LocalDate.now(); }

    public Users getUser() { return this.user; }
    public void setUser(Users x) { this.user = x; }
}
