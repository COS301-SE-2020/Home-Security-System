package com.springboot.SpringBackend.model;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "notification")
public class Notification implements Serializable {
    private static final long serialVersionUID = -693058768293344103L;

    public enum personType {
        White, Grey, Black;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notification_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="image_id", nullable = true)
    private Image notificationImg = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "listed", nullable = false)
    private personType listed;
    //private String listed;

    @Column(name = "msg", nullable = false)
    private String message;

    @Column(name = "ondate", nullable = false)
    private LocalDate onDate;

    @Column(name = "attime", nullable = false)
    private LocalTime atTime;

    @Column(name = "notificationdeleted", nullable = true)
    private LocalDate notificationDeleted = null;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    //@ManyToMany(mappedBy = "notificationList")
    //private List<Users> userList = new ArrayList<>();

    public Notification() {}

    public Notification(Image img, String msg) {
        this.notificationImg = img;
        this.listed = personType.Grey;
        this.message = Jsoup.clean(msg, Whitelist.simpleText());
        onDate = LocalDate.now();
        atTime = LocalTime.now();
    }

    public Notification(Image img, String listed, String msg) {
        this.notificationImg = img;

        if(listed.equalsIgnoreCase("White"))
        {
            //this.listed = "White";
            this.listed = personType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            //this.listed = "Black";
            this.listed = personType.Black;
        }
        else
        {
            //this.listed = "Grey";
            this.listed = personType.Grey;
        }

        this.message = Jsoup.clean(msg, Whitelist.simpleText());
        onDate = LocalDate.now();
        atTime = LocalTime.now();
    }

    public Notification(Image img, String msg, User u) {
        this.notificationImg = img;
        this.listed = personType.Grey;
        this.message = Jsoup.clean(msg, Whitelist.simpleText());
        this.onDate = LocalDate.now();
        this.atTime = LocalTime.now();
        this.user = u;
    }

    public Notification(Image img, String listed, String msg, User u) {
        this.notificationImg = img;

        if(listed.equalsIgnoreCase("White"))
        {
            //this.listed = "White";
            this.listed = personType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            //this.listed = "Black";
            this.listed = personType.Black;
        }
        else
        {
            //this.listed = "Grey";
            this.listed = personType.Grey;
        }

        this.message = Jsoup.clean(msg, Whitelist.simpleText());
        this.onDate = LocalDate.now();
        this.atTime = LocalTime.now();
        this.user = u;
    }

    public long getNotificationId() {
        return this.id;
    }
    public void setNotificationId(Long id) {
        this.id = id;
    }

    public Long getNotificationImgId() { return this.notificationImg.getImageId(); }
    public Image getNotificationImg() { return this.notificationImg; }
    public void setNotificationImg(Image img) {
        if (img != null) {
            this.notificationImg = img;
        }
    }

    public String getListed() { return this.listed.toString(); }
    public void setListed(String listed) {
        if(listed.equalsIgnoreCase("White"))
        {
            //this.listed = "White";
            this.listed = personType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            //this.listed = "Black";
            this.listed = personType.Black;
        }
        else
        {
            //this.listed = "Grey";
            this.listed = personType.Grey;
        }
    }

    public String getMessage() {
        return this.message;
    }
    public void setMessage(String msg) {
        this.message = Jsoup.clean(msg, Whitelist.simpleText());
    }

    public LocalDate getOnDate() { return this.onDate; }
    public void setOnDate(LocalDate date) { this.onDate = date; }

    public LocalTime getAtTime() {
        return this.atTime;
    }
    public void setAtTime(LocalTime time) { this.atTime = time; }

    public LocalDate getNotificationDeleted() {
        if(notificationDeleted != null) {
            return this.notificationDeleted;
        }
        return null;
    }
    public void setNotificationDeleted(LocalDate date) {
        if (date != null) {
            this.notificationDeleted = LocalDate.now();
        }
        else {
            this.notificationDeleted = null;
        }
    }

    public Long getUserId() { return this.user.getUserId(); }
    public User getUser() { return this.user; }
    public void setUser(User x) { this.user = x; }
}
