package com.springboot.SpringBackend.model;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "face")
public class Face implements Serializable {

    private static final long serialVersionUID = -7446583075938108797L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "face_id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;

    @Column(name = "features", nullable = false)
    private String features;

    @Column(name = "facedeleted", nullable = true)
    private LocalDate faceDeleted = null;

    public Face() {}

    public Face(Person p, String str) {
        this.person = p;
        this.features = Jsoup.clean(str, Whitelist.simpleText());
    }

    public Long getFaceId() { return this.id; }
    public void setFaceId(Long fid) { this.id = fid; }

    public Long getPersonId() { return this.person.getPersonId(); }
    public Person getPerson() {
        return this.person;
    }
    public void setPerson(Person p) {
        this.person = p;
    }

    public String getFeatures() {
        return this.features;
    }
    public void setFeatures(String str) {
        this.features = Jsoup.clean(str, Whitelist.simpleText());
    }

    public LocalDate getFaceDeleted() {
        if(faceDeleted != null) {
            return this.faceDeleted;
        }
        return null;
    }
    public void setFaceDeleted(LocalDate date) {
        if (date != null) {
            this.faceDeleted = LocalDate.now();
        } else {
            this.faceDeleted = null;
        }
    }
}
