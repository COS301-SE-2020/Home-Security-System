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
    private static final long serialVersionUID = 3055690177451933044L;

    public enum notificationType {
        Suspicious, Threat
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notification_id", nullable = false)
    private Long id;

    @Column(name = "image", nullable = false)
    private String notificationImg;

    @Enumerated(EnumType.STRING)
    @Column(name = "listed", nullable = false)
    private notificationType listed;
    //private String listed;

    @Column(name = "msg", nullable = false)
    private String message;

    @Column(name = "ondate", nullable = false)
    private LocalDate onDate;

    @Column(name = "attime", nullable = false)
    private LocalTime atTime;

    @Column(name = "notificationdeleted")
    private LocalDate notificationDeleted = null;

    @ManyToOne
    @JoinColumn(name="network_id", nullable = false)
    private Network network;

    public Notification() {}


    public Notification(String img, String msg, Network n) {
        this.notificationImg = img;
        this.listed = notificationType.Suspicious;
        this.message = Jsoup.clean(msg, Whitelist.simpleText());
        this.onDate = LocalDate.now();
        this.atTime = LocalTime.now();
        this.network = n;
    }

    public Notification(String img, String listed, String msg, Network n) {
        this.notificationImg = img;

        if(listed.equalsIgnoreCase("Suspicious"))
        {
            //this.listed = "Suspicious";
            this.listed = notificationType.Suspicious;
        }
        else
        {
            //this.listed = "Threat";
            this.listed = notificationType.Threat;
        }

        this.message = Jsoup.clean(msg, Whitelist.simpleText());
        this.onDate = LocalDate.now();
        this.atTime = LocalTime.now();
        this.network = n;
    }

    public Long getNotificationId() {
        return this.id;
    }
    public void setNotificationId(Long id) {
        this.id = id;
    }

    // public Long getNotificationImgId() { return this.notificationImg.getImageId(); }
    public String getNotificationImg() { return this.notificationImg; }
    public void setNotificationImg(String img) { this.notificationImg = img; }

    public String getListed() { return this.listed.toString(); }
    public void setListed(String listed) {
        if(listed.equalsIgnoreCase("Suspicious"))
        {
            //this.listed = "Suspicious";
            this.listed = notificationType.Suspicious;
        }
        else
        {
            //this.listed = "Threat";
            this.listed = notificationType.Threat;
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

    public Long getNetworkId() { return this.network.getNetworkId(); }
    public Network getNetwork() { return this.network; }
    public void setNetwork(Network x) { this.network = x; }
}
