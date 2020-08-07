package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "argususer")
public class User implements Serializable {
    private static final long serialVersionUID = -2292162305534844772L;

    public enum UserRole {
        Admin, Advanced, Basic;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    //@Column(name = "profilePhoto", nullable = true)
    @ManyToOne
    @JoinColumn(name="profilephoto", nullable = true)
    private Image profilePhoto = null;

    @Column(name = "fname", nullable = false)
    private String name;

    @Column(name = "lname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "userpass", nullable = false)
    @Size(min=8)
    private String userPass;

    @Enumerated(EnumType.STRING)
    @Column(name = "userrole", nullable = false)
    private UserRole userRole;
    //private String userRole;

    @Column(name = "notifyemail", nullable = false)
    private Boolean notifyEmail;

    @Column(name = "notifylocal", nullable = false)
    private Boolean notifyLocal;

    @Column(name = "userdeleted", nullable = true)
    private LocalDate userDeleted = null;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @BatchSize(size = 1000)
    @JsonIgnore
    private List<Notification> notificationList = new ArrayList<>();

    /*
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usernotification",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id"))
    private List<Notification> notificationList = new ArrayList<>();
     */

    public User() { }

    public User(Image id, String name, String surname, String email, String username, String password, String role) {
        this.profilePhoto = id;
        this.name = Jsoup.clean(name, Whitelist.simpleText());
        this.surname = Jsoup.clean(surname, Whitelist.simpleText());
        this.email = Jsoup.clean(email, Whitelist.simpleText());
        this.username = Jsoup.clean(username, Whitelist.simpleText());
        this.userPass = Jsoup.clean(password, Whitelist.simpleText());

        if(role.equalsIgnoreCase("Admin"))
        {
            this.userRole = UserRole.Admin;
            //this.userRole = "Admin";
        }
        else if(role.equalsIgnoreCase("Advanced"))
        {
            this.userRole = UserRole.Advanced;
            //this.userRole = "Advanced";
        }
        else
        {
            this.userRole = UserRole.Basic;
            //this.userRole = "Basic";
        }

        this.notifyEmail = true;
        this.notifyLocal = true;
    }

    public User(String name, String surname, String email, String username, String password, String role) {
        this.name = Jsoup.clean(name, Whitelist.simpleText());
        this.surname = Jsoup.clean(surname, Whitelist.simpleText());
        this.email = Jsoup.clean(email, Whitelist.simpleText());
        this.username = Jsoup.clean(username, Whitelist.simpleText());
        this.userPass = Jsoup.clean(password, Whitelist.simpleText());

        if(role.equalsIgnoreCase("Admin"))
        {
            this.userRole = UserRole.Admin;
            //this.userRole = "Admin";
        }
        else if(role.equalsIgnoreCase("Advanced"))
        {
            this.userRole = UserRole.Advanced;
            //this.userRole = "Advanced";
        }
        else
        {
            this.userRole = UserRole.Basic;
            //this.userRole = "Basic";
        }

        this.notifyEmail = true;
        this.notifyLocal = true;
    }

    public Long getUserId() {
        return this.id;
    }
    public void setUserId(Long id) {
        this.id = id;
    }


    public Long getProfilePhotoId() {
        if (profilePhoto != null)
        {
            return this.profilePhoto.getImageId();
        }
        return null;
    }
    public Image getProfilePhoto() {
        if (profilePhoto != null)
        {
            return this.profilePhoto;
        }
        return null;
    }
    public void setProfilePhoto(Image photo) {
        if (photo != null) {
            this.profilePhoto = photo;
        }
    }

    public String getName() {return this.name;}
    public void setName(String name) {this.name = Jsoup.clean(name, Whitelist.simpleText());}

    public String getSurname() {return this.surname;}
    public void setSurname(String name) {this.surname = Jsoup.clean(name, Whitelist.simpleText());}

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = Jsoup.clean(email, Whitelist.simpleText());
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String name) {
        this.username = Jsoup.clean(name, Whitelist.simpleText());
    }

    public String getUserPass() {
        return this.userPass;
    }
    public void setUserPass(String pass) {
        this.userPass = Jsoup.clean(pass, Whitelist.simpleText());
    }

    public String getUserRole() { return this.userRole.toString(); }
    public void setUserRole(String role) {
        if(role.equalsIgnoreCase("Admin"))
        {
            this.userRole = UserRole.Admin;
            //this.userRole = "Admin";
        }
        else if(role.equalsIgnoreCase("Advanced"))
        {
            this.userRole = UserRole.Advanced;
            //this.userRole = "Advanced";
        }
        else
        {
            this.userRole = UserRole.Basic;
            //this.userRole = "Basic";
        }
    }

    public Boolean getNotifyEmail() {
        return this.notifyEmail;
    }
    public void setNotifyEmail(Boolean tf) {
        this.notifyEmail = tf;
    }

    public Boolean getNotifyLocal() {
        return this.notifyLocal;
    }
    public void setNotifyLocal(Boolean tf) {
        this.notifyLocal = tf;
    }

    public LocalDate getUserDeleted() {
        if(userDeleted != null) {
            return this.userDeleted;
        }
        return null;
    }
    public void setUserDeleted() {
        this.userDeleted = LocalDate.now();
    }

    public List<Notification> getNotificationList() { return this.notificationList; }
    public void setNotificationList(List<Notification> list) { this.notificationList = list; }
}
