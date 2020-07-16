package com.springboot.SpringBackend.dto;

import com.springboot.SpringBackend.model.Image;
import com.springboot.SpringBackend.model.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class NotificationDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String message;
    private String onDate;
    private String atTime;
    private String deletionDate;

    private Image notificationImg;
    private User user;

    public NotificationDTO() {}

    public NotificationDTO(String msg, Image img, User u) {
        this.message = msg;
        this.onDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.atTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        this.notificationImg = img;
        this.user = u;
    }

    public Long getNotificationId() {
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

    public long getImageById() { return this.notificationImg.getImageId(); }
    public Image getImage() { return this.notificationImg; }
    public void setImage(Image img) { this.notificationImg = img; }

    public Long getUserById() { return this.user.getUserId(); }
    public User getUser() { return this.user; }
    public void setUser(User u) { this.user = u; }

    public String getDeletionDate() {
        return this.deletionDate;
    }
    public void setDeletionDate(String date) {
        this.deletionDate = date;
    }

    @Override
    public String toString() {
        return "Person [notification_ID=" + id + ", message=" + message +
                ", onDate=" + onDate + ", atTime=" + atTime +
                ", photo_ID=" + notificationImg.getImageId() +
                ", user_ID=" + user.getUserId() + ", deletionDate=" + deletionDate + "]";
    }
}
