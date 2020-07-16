package com.springboot.SpringBackend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Person.class)*/
@Table(name = "vehicle_table")
public class Vehicle {
    public enum vehicleType implements Serializable {
        White, Grey, Black;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_id", nullable = false)
    private Long id;

    @Column(name = "listed", nullable = false)
    private vehicleType listed;

    @Column(name = "licenseNo", nullable = false)
    private String licenseNo;

    @Column(name = "created", nullable = false)
    private String created;

    @Column(name = "deletionDate", nullable = true)
    private String deletionDate = "";

    //@Column(name = "photo_ID", nullable = false)
    @OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "photo_id")
    private Image vehicleImg;

    @OneToMany(mappedBy = "vehicleperson")
    private List<PersonVehicle> personList;

    public Vehicle() { }

    public Vehicle(String listed, String licenseNo, Image img) {
        if(listed.equalsIgnoreCase("White"))
        {
            this.listed = vehicleType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            this.listed = vehicleType.Black;
        }
        else
        {
            this.listed = vehicleType.Grey;
        }

        this.licenseNo = licenseNo;
        this.created = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.vehicleImg = img;
    }

    public Vehicle(String licenseNo, Image img) {
        this.listed = vehicleType.Grey;
        this.licenseNo = licenseNo;
        this.created = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.vehicleImg = img;
    }

    public Long getVehicleId() {
        return this.id;
    }
    public void setVehicleId(Long id) {
        this.id = id;
    }

    public String getListed() {
        return this.listed.toString();
    }
    public void setListed(String listed) {
        if(listed.equalsIgnoreCase("White"))
        {
            this.listed = vehicleType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            this.listed = vehicleType.Black;
        }
        else
        {
            this.listed = vehicleType.Grey;
        }
    }

    public String getLicenseNo() {
        return this.licenseNo;
    }
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getCreated() {
        return this.created;
    }
    public void setCreated(String date) {
        this.created = date;
    }

    public Long getPhotoById() { return this.vehicleImg.getImageId(); }
    public Image getPhoto() {
        return this.vehicleImg;
    }
    public void setPhoto(Image img) {
        if (img != null) {
            this.vehicleImg = img;
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
        return "Vehicle [vehicle_IDid=" + id + ", listed=" + listed +
                ", licenseNo=" + licenseNo + ", created=" + created +
                ", photo_ID=" + vehicleImg.getImageId() +
                ", deletionDate=" + deletionDate + "]";
    }
}
