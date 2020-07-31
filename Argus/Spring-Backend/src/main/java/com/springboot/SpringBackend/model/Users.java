package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Person.class)*/
@JsonIgnoreProperties(
        value = {"notificationList"},
        allowGetters = true,
        allowSetters = true
)
@Table(name = "users")
public class Users{
    public enum UserRole implements Serializable {
        Admin, Advanced, Basic;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;
    //@Column(name = "profilePhoto", nullable = true)
    @ManyToOne
    @JoinColumn(name="profilephoto", nullable = true)
    private Image profilePhoto;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "userpass", nullable = false)
    private String userPass;
    @Enumerated(EnumType.STRING)
    @Column(name = "userrole", nullable = false)
    //private String userRole;
    private UserRole userRole;
    @Column(name = "notifyemail", nullable = false)
    private Boolean notifyEmail = true;
    @Column(name = "notifylocal", nullable = false)
    private Boolean notifyLocal = true;
    @Column(name = "userdeleted", nullable = true)
    private LocalDate userDeleted;
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @BatchSize(size = 1000)
    private List<Notification> notificationList;

    /*
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usernotification",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id"))
    private List<Notification> notificationList = new ArrayList<>();
     */

    public Users() { }

    public Users(Image id, String name, String surname, String email, String username, String password, String role) {
        this.profilePhoto = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.userPass = password;

        if(role.equalsIgnoreCase("Admin"))
        {
            this.userRole = UserRole.Admin;
        }
        else if(role.equalsIgnoreCase("Advanced"))
        {
            this.userRole = UserRole.Advanced;
        }
        else
        {
            this.userRole = UserRole.Basic;
        }

        this.notifyEmail = true;
        this.notifyLocal = true;
    }

    public Users(String name, String surname, String email, String username, String password, String role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.userPass = password;

        if(role.equalsIgnoreCase("Admin"))
        {
            this.userRole = UserRole.Admin;
        }
        else if(role.equalsIgnoreCase("Advanced"))
        {
            this.userRole = UserRole.Advanced;
        }
        else
        {
            this.userRole = UserRole.Basic;
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

    public Image getProfilePhoto() { return this.profilePhoto; }
    public void setProfilePhoto(Image photo) {
        if (photo != null) {
            this.profilePhoto = photo;
        }
    }

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public String getSurname() {return this.surname;}
    public void setSurname(String name) {this.surname = name;}

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String name) {
        this.username = name;
    }

    public String getUserPass() {
        return this.userPass;
    }
    public void setUserPass(String pass) {
        this.userPass = pass;
    }

    public String getUserRole() { return this.userRole.toString(); }
    public void setUserRole(String role) {
        if(role.equalsIgnoreCase("Admin"))
        {
            this.userRole = UserRole.Admin;
        }
        else if(role.equalsIgnoreCase("Advanced"))
        {
            this.userRole = UserRole.Advanced;
        }
        else
        {
            this.userRole = UserRole.Basic;
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
        return this.userDeleted;
    }
    public void setUserDeleted() {
        this.userDeleted = LocalDate.now();
    }

    public List<Notification> getNotificationList() { return this.notificationList; }
    public void setNotificationList(List<Notification> list) { this.notificationList = list; }
}
