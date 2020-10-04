package com.springboot.SpringBackend.model;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "argususer")
/*@Table(name = "argususer", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})*/
public class User implements Serializable {
    private static final long serialVersionUID = -2292162305534844772L;

    public enum UserRole {
        Admin, Advanced, Basic
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    // @ManyToOne
    // @JoinColumn(name="profilephoto", nullable = false)
    @Column(name = "profilephoto", nullable = false)
    private String profilePhoto;

    @Size(max = 35)
    @Column(name = "fname", nullable = false)
    private String fname;

    @Size(max = 35)
    @Column(name = "lname", nullable = false)
    private String lname;

    @Size(min = 10, max = 12)
    @Column(name = "contactno", nullable = false)
    private String contactNo;

    @Email
    @Size(max = 50)
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 12)
    @Column(name = "username", nullable = false)
    private String username;

    @Size(min=8)
    @Column(name = "userpass", nullable = false)
    private String userPass;

    @Enumerated(EnumType.STRING)
    @Column(name = "userrole", nullable = false)
    private UserRole userRole;
    //private String userRole;

    @Column(name = "securequestion", nullable = false)
    private String secureQuestion;

    @Size(max=35)
    @Column(name = "secureanswer", nullable = false)
    private String secureAnswer;

    @Column(name = "notifyemail", nullable = false)
    private Boolean notifyEmail;

    @Column(name = "notifysms", nullable = false)
    private Boolean notifySMS;

    @Column(name = "userdeleted")
    private LocalDate userDeleted = null;

    @ManyToOne
    @JoinColumn(name="network_id", nullable = false)
    private Network network;

    public User() { }

    public User(String id, String name, String surname, String contact, String email, String username,
                String password, String role, String answer, String question, Network n) {
        this.profilePhoto = Jsoup.clean(id, Whitelist.simpleText());
        this.fname = Jsoup.clean(name, Whitelist.simpleText());
        this.lname = Jsoup.clean(surname, Whitelist.simpleText());
        this.contactNo = Jsoup.clean(contact, Whitelist.simpleText());
        this.email = Jsoup.clean(email, Whitelist.simpleText());
        this.username = Jsoup.clean(username, Whitelist.simpleText());
        this.userPass = Jsoup.clean(password, Whitelist.simpleText());
        this.secureQuestion = Jsoup.clean(question, Whitelist.simpleText());
        this.secureAnswer = Jsoup.clean(answer, Whitelist.simpleText());
        this.network = n;

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
        this.notifySMS = false;
    }

    public User(String name, String surname, String contact, String email, String username,
                String password, String role, String answer, String question , Network n) {
        this.profilePhoto = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAUDBAQEAwUEBAQFBQUGBwwIBwcHBw8LCwkMEQ8SEhEPERETFhwXExQaFRERGCEYGh0dHx8fExciJCIeJBweHx7/2wBDAQUFBQcGBw4ICA4eFBEUHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh7/wAARCAD6APoDASIAAhEBAxEB/8QAGwABAQACAwEAAAAAAAAAAAAAAAECBgQFBwP/xAA6EAACAQIFAgQEBAMHBQAAAAAAAQIDEQQFEiExBkEiUWFxEzKhsSOBkfBiwdEUFSQ0NXPhQmNysvH/xAAZAQEBAQEBAQAAAAAAAAAAAAAAAQMCBAX/xAAgEQEBAAIDAQACAwAAAAAAAAAAAQIRAyExEjJBBCJh/9oADAMBAAIRAxEAPwD3gAh9JioBAKQpAKAQCghQAIABSN2CvbdWDmxQN/2w9lvsPmGgD23Fn5fUakTdALbEDqKAAoCFAEKABCggAACFAAEKCiAAAAAAAAAB/JtyRLQDTLSnqSMlTqSa0Up1W+8FsLZCVF8y22Cg51NMKU6r8kd1lXT+JxLU8R+HS2bVtzaMBl+DwcFHD0Yprmcoq/6mOXPJ07mLScPlGY1n4cHp92jlR6czbn4dO3k2v6m+JL0FkYXltX5ef1sizSC3w0X7Nf1OJUwWKpXVTCVE1y+x6YfKrGLspR1LysdY81nq/MeYyWl2aafkyHoGNybAYlO9GEJNcxgkzWM1yGtg4yqUtVaF+y3RvjzTJxlNOmBlvfhX4s+xLWbXc19cy7QABQpCkAAAAAAAAAEKUQAAAAAAAAi2bdtkimeHg6uKpUkr6nuc26hJt3PTuSPFqNfEbUpbpeZtmGwOFw8dNGhCK9jLCUYYfDU6cIpaUkfc8Gedt00mIoJcfoGn6P0LFmRHSLjgFAEJZ3vcyAGMk7bcmDgmmrKz5v3Po+Ccku53E68ar1PksVCeMwsNLjvKKRq90rT38Wz9D0+pFTdpK6S3Xnc0PqPAvA5nOy/Bq3kttk/2z1cHJvqss8deOsAB6UCkKFAAQAAAAAAhSFAAAAAAAAA5/TkFPPMPGSut/szgHa9JK+eUX5J/ZnGf40b3FJ89zIx/6V5mR86etp4R7lJHuUs8AAFAAABYAlGMlurcnR9X4VV8sc4xvKm3K/sd676lY4uaRjLLsRDzpy+zO8OqlebJ3KXZTqW3SdjGDbij3slKQpQABAAAAAAQFIUAAAAAAAADuOkP9ah/4v7M6c7fpJ2zul6pr6M4z/GjeV8y92ZLgx9TJcM+dPa2nhDllJHllLj4QABQAAAAXII15HFzGtCjharqTS8EtvPY5TbRpfW7/wAbTgm7NXf0O+OfWWolunRTkp1pTjtGU39yLixNK0KC4TuXsl5HvZBSFKAAIAAAAAAQpCgAAAAAAElez08gVbnadKu2e0Pz/wDVnV7pWTsu5zun2v75oNOdrtbJ24Zxn4PQk9jJcGOzaMlwz509raeEOWUkOWUuPhAAFAAAGQrJ2AkjSes5XzmMeyg/sjdnz6s0Tq2Ted1b30qMUvexp/G/Nxm6kAHucBSFAAAgAAAAABCgogAAAAAVbO6IG9O/kMf9XSOLnDQ9m5WTPQsqyvDYbD0dNOGuO7k47t2sefprXL/tSu/3+Z6ZgKyr4SnWjxNXR5ebLKLJ2+unfkqVgDz6aCVgANaFIAAKQABYACNb8nV51lWHxOFrzcUqmnUpabu6TO0tucfM6vwsDiJeVKT+hcLZeks282aack+zsRO8U/Mrlql8R8Sm/uGkkrH0GSFIUoAAgAAAAADAIUAAAAAAkt00UBWT8Tq+qN56SxCrZPTV96T0/Rf1NEb4t+ZsPRWKjSxNXDybtU3j5X/aMeaf1WXtuC878mR81e8dnaPPqfQ8bQAAFIAAKQAAwHuTLwY359Nzpurq6o5XKKfiquy9v2zuJPZq3bc07rPFRq4ulh4NyVJPVbz2NODG2pXQAA97IKQoAAEAAAAAAAYKIAAAAAAAAIuXxFom4SjumA+LvhMfP10jduksZVxOBlKvLVKMrfY700rorE/Cxzw0pbVLtL8n/Q3O915Hz+XH5rWXpkncGMOWvIyOIsAUhVACgR8BcB8GF2lFc3Gtpa6rqqtOhllSVGeibsrmiykpTctTdV21PzNk66xGqeHwyfEm5rz4sayvmn5329j2cE1HFqgA2chSFAAAgAAAAABCgogAAAAAAAAl8qXm9wBLpK+mFrSw2LjiYcwe3seiYLEwxeEp14O6kkzzfubN0TXrOrVoSk3BfLvxyefmx/a43fTbI8spjC/D3MjyttaAAAAAB8Hyq1Iwpym3ZRjqufV8HS9V1Z0smquk7P5fqkXGbrnJqOcYp4zH1ayd1e0fa5xXbZrl8kirQim7vSr+4PfjNRlOwAHShSFIAAAAAAAAADIUAAAAAAAAAgVbkSo+5s3QsLyxNR+Ube+9zWXxubj0VT0Zc5tW1Tf6djHm8MPWwQ22MiRKeRuAAAAAJLg6jqqF8mr6edSf1VzuHwcHOIfFy+tC1/A39DrH1zl486j39wErXT2a59BZ2vZ2Pdj4ygADpQpCgAAQAAAAAAhQUQAAAAAAC57/AJAA7225Yfh+dq3kuQ7pX2S5uyal9c2bSSaioWvJs9DyPD/2fLcLBqzdNOXvZGp9NZdPG41V5RksPF/M1y/3Y3uENKsnt29Dy82U8jTjmlg739ygHnjQABQAAB8HxqR1wUWtpxd/0Ps+DFxvFq9hPUrzXH03h8zxNKasviSt7Xdj4eLjsbR1flc5pYrDxcnFeNLy8/uas25S8HC2a7pnv47MoyuKgXTdk7S9Q9ubnYFIUAACAAAAAAEBSiAFI5t0gKkm9xtfxPfsNkqDfs7Psz64bD1sTV0U4Oc3+iNiyzpi1p4+erygmc5ZyLN1ruEw9bFVFDCUviz7s2TLOmqcbTx1T4iv8l+5sWGoU6NNU6dPTFHIjxzc8+fNb1GkxcalGnSpqlCKilxA5KKDz9u0BQUCFAEKABHwQyATT4zcVJqW11t6nS5t0/hcU5VaH4NZ7t35NgD4LjlcV083zHLcVgZfj0tVHtNHDXG0tS7eh6hOKkmnHUmdDmnTuHxEnPDv4VV7+jPTjzb9ZXHTTSnJzDL8VgXbERa/jRxrru/EbTKVxLQFaVrvnuYlLdKCAKoIAoO6QD42e/YoJq7V90Vbuy38ybqSSheb4R3WU5FisXpq118Kj3XdkucxNbdRh6VXEVPh0acpTvwjZMr6ak1Gri2n30o73LsDhsHCMcNTSj3bW7Oc7Pfc8nJzb8dzFx8Jg6WFpqFGCjbv3PtosnaT35b5M1urh8GG7XWpGNrLZv8AMsVYFjwXa7UAAQoAAhQAAAEKAAI+CkfAGKJpvdXdvqZFRNm4+VSjGpDRUSnH+I6LMum6NZOWH8E/WxsRNm77lxzuKfMrzbH4LE4KbjXpu3aS4Zxuyb2TPTMXRp146KlOM4P5ro1jNenZK9XLvlW7g/2j18fNP24uLWm0le//ACPL1Mq0Z06rp16WiV9kY738Xbg33tyAAgDxX1Rjqt28w2vD/E9jvekctWLqyxVSP4dKWlJr5nb/AJGdmETW65vTuRwioY3FrU5bxhJXtf8A+m0RilFJJJLtYwUNPCVuy8j6Ljk+fln9VtJ0tgARQE/MoEsUE/MCgAACFAAEAoAAAhQAAAlkUAAAAJZX4FkUgHTZ9k1HMKcnBRhXjupJbs0nFUqtKu6VSNnTelt9z0yUG00na7vc6Dq3K41cJLGU4pVKSbkkvmVt/sbcPJq9uM5tp4Jy0u7VzFzinb+R7d7ZeM7apaVzay9z0PIcOsLllGnp0y0py9zz7B/5uH+5/I9Ojwzx82VrTj8ZIpCnnkaAAKAAAAAAAAAAAAAAAAAAAAAAAAABAKCAAYVoRqU5QmrxkrNGfdkff2JL2sea5nReGx9am1bTO8fY+Pwovc7TrLbOp/7f8kdZH5V7H0OO7xebP1//2Q==";
        this.fname = Jsoup.clean(name, Whitelist.simpleText());
        this.lname = Jsoup.clean(surname, Whitelist.simpleText());
        this.contactNo = Jsoup.clean(contact, Whitelist.simpleText());
        this.email = Jsoup.clean(email, Whitelist.simpleText());
        this.username = Jsoup.clean(username, Whitelist.simpleText());
        this.userPass = Jsoup.clean(password, Whitelist.simpleText());
        this.secureQuestion = Jsoup.clean(question, Whitelist.simpleText());
        this.secureAnswer = Jsoup.clean(answer, Whitelist.simpleText());
        this.network = n;

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
        this.notifySMS = false;
    }

    public Long getUserId() {
        return this.id;
    }
    public void setUserId(Long id) {
        this.id = id;
    }

    // public Long getVehicleImgId() { return this.profilePhoto.getImageId(); }
    public String getProfilePhoto() {
        if (profilePhoto != null)
        {
            return this.profilePhoto;
        }
        return null;
    }
    public void setProfilePhoto(String photo) {
        if (photo != null) {
            this.profilePhoto = photo;
        }
    }

    public String getFname() {return this.fname; }
    public void setFname(String name) {this.fname = Jsoup.clean(name, Whitelist.simpleText());}

    public String getLname() {return this.lname; }
    public void setLname(String name) {this.lname = Jsoup.clean(name, Whitelist.simpleText());}

    public String getContactNo() {return this.contactNo; }
    public void setContactNo(String num) {this.contactNo = Jsoup.clean(num, Whitelist.simpleText());}

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

    public String getSecureQuestion() {return this.secureQuestion; }
    public void setSecureQuestion(String question) {this.secureQuestion = Jsoup.clean(question, Whitelist.simpleText());}


    public String getSecureAnswer() {return this.secureAnswer; }
    public void setSecureAnswer(String answer) {this.secureAnswer = Jsoup.clean(answer, Whitelist.simpleText());}


    public Boolean getNotifyEmail() {
        return this.notifyEmail;
    }
    public void setNotifyEmail(Boolean tf) {
        this.notifyEmail = tf;
    }

    public Boolean getNotifySMS() {
        return this.notifySMS;
    }
    public void setNotifySMS(Boolean tf) {
        this.notifySMS = tf;
    }

    public LocalDate getUserDeleted() {
        if(userDeleted != null) {
            return this.userDeleted;
        }
        return null;
    }
    public void setUserDeleted(LocalDate date) {
        if (date != null) {
            this.userDeleted = LocalDate.now();
        } else {
            this.userDeleted = null;
        }
    }

    public Long getNetworkId() { return this.network.getNetworkId(); }
    public Network getNetwork() { return this.network; }
    public void setNetwork(Network x) { this.network = x; }

}
