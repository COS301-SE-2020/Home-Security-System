package com.springboot.SpringBackend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Person.class)*/
@Table(name = "notification_table")
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notification_id", nullable = false)
    private Long id;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "onDate", nullable = false)
    private String onDate;
    @Column(name = "atTime", nullable = false)
    private String atTime;
    @Column(name = "deletionDate", nullable = true)
    private String deletionDate = "";

    //@Column(name = "photo_id", nullable = true)
    @OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "photo_id")
    private Image notificationImg;

    //@Column(name = "user_id", nullable = false)
    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Notification() {}

    public Notification(String msg, Image img, User u) {
        this.message = msg;
        onDate =LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        atTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        this.notificationImg = img;
        this.user = u;
    }

    public long getNotificationId() {
        return this.id;
    }
    public void setNotificationId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }
    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getOnDate() { return this.onDate; }
    public void setOnDate(String date) { this.onDate = date; }

    public String getAtTime() {
        return this.atTime;
    }
    public void setAtTime(String time) {
        this.atTime = time;
    }

    public Long getImageById() { return this.notificationImg.getImageId(); }
    public Image getImage() { return this.notificationImg; }
    public void setImage(Image img) {
        if (img != null) {
            this.notificationImg = img;
        }
    }

    public Long getUserById() { return this.user.getUserId(); }
    public User getUser() { return this.user; }
    public void setUser(User u) {
        if (u != null) {
            this.user = u;
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
        return "Person [notification_id=" + id + ", message=" + message +
                ", onDate=" + onDate + ", atTime=" + atTime +
                ", photo_id=" + notificationImg.getImageId() +
                ", user_id=" + user.getUserId() +
                ", deletionDate=" + deletionDate + "]";
    }
}
