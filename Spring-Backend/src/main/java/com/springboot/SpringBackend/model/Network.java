package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "network")
public class Network implements Serializable {
    private static final long serialVersionUID = 7192788037622789092L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "network_id", nullable = false)
    private Long id;

    @Size(max = 12)
    @Column(name = "netname", nullable = false)
    private String netName;

    @Size(min=10, max = 12)
    @Column(name = "securitynumber", nullable = false)
    private String securityNumber;

    @OneToMany(mappedBy="network", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Camera> camList = new ArrayList<>();

    @OneToMany(mappedBy="network", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> userList = new ArrayList<>();

    @OneToMany(mappedBy="network", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Notification> notificationList = new ArrayList<>();

    @OneToMany(mappedBy="network", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Person> personList = new ArrayList<>();

    public Network() { }

    public Network(String name) {
        if(validateInput(name)) {
            this.netName = name;
        }
        this.securityNumber = "+27840763231";
    }

    public Network(String name, String phoneNum) {
        if(validateInput(name)) {
            this.netName = name;
        }
        if(validateNumber(phoneNum)) {
            this.securityNumber = Jsoup.clean(phoneNum, Whitelist.simpleText());
        }
    }

    public Long getNetworkId() {
        return this.id;
    }
    public void setNetworkId(Long id) {
        this.id = id;
    }

    public String getNetName() {
        return this.netName;
    }
    public void setNetName(String name) {
        if(validateInput(name)) {
            this.netName = Jsoup.clean(name, Whitelist.simpleText());
        }
    }

    public String getSecurityNumber() {
        return this.securityNumber;
    }
    public void setSecurityNumber(String phoneNum) {
        if(validateNumber(phoneNum)) {
            this.securityNumber = Jsoup.clean(phoneNum, Whitelist.simpleText());
        }
    }

    public List<Camera> getCamList() { return this.camList; }
    public void setCamList(List<Camera> list) { this.camList = list; }

    public List<User> getUserList() { return this.userList; }
    public void setUserList(List<User> list) { this.userList = list; }

    public List<Notification> getNotificationList() { return this.notificationList; }
    public void setNotificationList(List<Notification> list) { this.notificationList = list; }

    public List<Person> getPersonList() { return this.personList; }
    public void setPersonList(List<Person> list) { this.personList = list; }

    private Boolean validateInput(String str) {
        return str.matches("\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+");
    }

    private Boolean validateNumber(String num)
    {
        return num.matches("^((?:\\+27))(=|72|82|73|83|74|84|79)(\\d{7})$");
    }
}
