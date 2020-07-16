package com.springboot.SpringBackend.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Person.class)*/
@Table(name = "user_table")
public class User{
    public enum UserRole implements Serializable {
        Admin, Advanced, Basic;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "userPass", nullable = false)
    private String userPass;

    @Column(name = "userRole", nullable = false)
    private UserRole userRole;

    @Column(name = "deletionDate", nullable = true)
    private String deletionDate = "";

    //@Column(name = "profilePhoto", nullable = false)
    @OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "profilePhoto", referencedColumnName = "photo_ID")
    private Image profilePhoto;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Notification notification;

    public User() { }

    public User(String name, String email, String username, String password, String role, Image id) {
        this.fullname = name;
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

        this.profilePhoto = id;
    }

    public User(String name, String email, String username, String password, String role) {
        this.fullname = name;
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
    }

    public Long getUserId() {
        return this.id;
    }
    public void setUserId(Long id) {
        this.id = id;
    }

    public String getName() {return this.fullname;}
    public void setName(String name) {this.fullname = name;}

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

    public String getPassword() {
        return this.userPass;
    }
    public void setPassword(String pass) {
        this.userPass = pass;
    }

    public String getRole() {
        return this.userRole.toString();
    }
    public void setRole(String role) {
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

    public Long getProfilePhotoById() { return this.profilePhoto.getImageId(); }
    public Image getProfilePhoto() { return this.profilePhoto; }
    public void setProfilePhoto(Image photo) {
        if (photo != null) {
            this.profilePhoto = photo;
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
        return "User [user_id=" + id + ", fullname=" + fullname +
                ", email=" + email + ", username=" + username +
                ", userPass=" + userPass + ", userRole=" + userRole +
                ", profilePhoto=" + profilePhoto.getImageId() +
                ", deletionDate=" + deletionDate + "]";
    }
}
