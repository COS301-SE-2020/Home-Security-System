package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {
    private static final long serialVersionUID = -5448294771628276088L;

    public enum vehicleType {
        White, Grey, Black
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_id", nullable = false)
    private Long id;

    // @ManyToOne
    // @JoinColumn(name="image_id", nullable = true)
    @Column(name = "image", nullable = true)
    private String vehicleImg;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehiclelisted", nullable = false)
    private vehicleType vehicleListed;
    //private String vehicleListed;

    @Column(name = "vehiclemake", nullable = false)
    private String vehicleMake;

    @Column(name = "vehiclemodel", nullable = false)
    private String vehicleModel;

    @Column(name = "vehiclecolour", nullable = false)
    private String vehicleColour;

    @Column(name = "licenseno", nullable = false)
    private String licenseNo;

    @Column(name = "vehiclecreated", nullable = false)
    private LocalDate vehicleCreated;

    @Column(name = "vehicledeleted", nullable = true)
    private LocalDate vehicleDeleted = null;

    @ManyToOne
    @JoinColumn(name="person_id", nullable = true)
    private Person person;

    @ManyToOne
    @JoinColumn(name="network_id", nullable = false)
    private Network network;

    //@ManyToMany(mappedBy = "vehicleList")
    //private List<Person> personList = new ArrayList<>();

    public Vehicle() { }

    public Vehicle(String img, Network n) {
        this.vehicleImg = img;
        this.vehicleListed = vehicleType.Grey;
        this.licenseNo = "Unknown";
        this.vehicleMake = "Unknown";
        this.vehicleModel = "Unknown";
        this.vehicleColour = "Unknown";
        this.vehicleCreated = LocalDate.now();
        this.network = n;
    }

    public Vehicle(String img, String listed, String make, String model,
                   String colour, String licenseNo, Network n) {
        this.vehicleImg = img;

        if(listed.equalsIgnoreCase("White"))
        {
            //this.vehicleListed = "White";
            this.vehicleListed = vehicleType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            //this.vehicleListed = "Black";
            this.vehicleListed = vehicleType.Black;
        }
        else
        {
            //this.vehicleListed = "Grey";
            this.vehicleListed = vehicleType.Grey;
        }

        this.licenseNo = Jsoup.clean(licenseNo, Whitelist.simpleText());
        this.vehicleMake = Jsoup.clean(make, Whitelist.simpleText());
        this.vehicleModel = Jsoup.clean(model, Whitelist.simpleText());
        this.vehicleColour = Jsoup.clean(colour, Whitelist.simpleText());
        this.vehicleCreated = LocalDate.now();
        this.network = n;
    }

    public Vehicle(String img, String licenseNo, String make, String model,
                   String colour, Network n) {
        this.vehicleImg = img;
        //this.vehicleListed = "Grey";
        this.vehicleListed = vehicleType.Grey;
        this.licenseNo = Jsoup.clean(licenseNo, Whitelist.simpleText());
        this.vehicleMake = Jsoup.clean(make, Whitelist.simpleText());
        this.vehicleModel = Jsoup.clean(model, Whitelist.simpleText());
        this.vehicleColour = Jsoup.clean(colour, Whitelist.simpleText());
        this.vehicleCreated = LocalDate.now();
        this.network = n;
    }

    public Vehicle(String img, String listed, String licenseNo, String make, String model,
                   String colour, Person p, Network n) {
        this.vehicleImg = img;

        if(listed.equalsIgnoreCase("White"))
        {
            //this.vehicleListed = "White";
            this.vehicleListed = vehicleType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            //this.vehicleListed = "Black";
            this.vehicleListed = vehicleType.Black;
        }
        else
        {
            //this.vehicleListed = "Grey";
            this.vehicleListed = vehicleType.Grey;
        }

        this.licenseNo = Jsoup.clean(licenseNo, Whitelist.simpleText());
        this.vehicleMake = Jsoup.clean(make, Whitelist.simpleText());
        this.vehicleModel = Jsoup.clean(model, Whitelist.simpleText());
        this.vehicleColour = Jsoup.clean(colour, Whitelist.simpleText());
        this.vehicleCreated = LocalDate.now();
        this.person = p;
        this.network = n;
    }

    public Vehicle(String img, String licenseNo, String make, String model,
                   String colour, Person p, Network n) {
        this.vehicleImg = img;
        //this.vehicleListed = "Grey";
        this.vehicleListed = vehicleType.Grey;
        this.licenseNo = Jsoup.clean(licenseNo, Whitelist.simpleText());
        this.vehicleMake = Jsoup.clean(make, Whitelist.simpleText());
        this.vehicleModel = Jsoup.clean(model, Whitelist.simpleText());
        this.vehicleColour = Jsoup.clean(colour, Whitelist.simpleText());
        this.vehicleCreated = LocalDate.now();
        this.person = p;
        this.network = n;
    }

    public Long getVehicleId() {
        return this.id;
    }
    public void setVehicleId(Long id) {
        this.id = id;
    }

    // public Long getVehicleImgId() { return this.vehicleImg.getImageId(); }
    public String getVehicleImg() {
        return this.vehicleImg;
    }
    public void setVehicleImg(String img) {
        if (img != null) {
            this.vehicleImg = img;
        }
    }

    public String getVehicleListed() {
        return this.vehicleListed.toString();
    }
    public void setVehicleListed(String listed) {
        if(listed.equalsIgnoreCase("White"))
        {
            //this.vehicleListed = "White";
            this.vehicleListed = vehicleType.White;
        }
        else if(listed.equalsIgnoreCase("Black"))
        {
            //this.vehicleListed = "Black";
            this.vehicleListed = vehicleType.Black;
        }
        else
        {
            //this.vehicleListed = "Grey";
            this.vehicleListed = vehicleType.Grey;
        }
    }

    public String getVehicleMake() {
        return this.vehicleMake;
    }
    public void setVehicleMake(String make) {
        this.vehicleMake = Jsoup.clean(make, Whitelist.simpleText());
    }

    public String getVehicleModel() {
        return this.vehicleModel;
    }
    public void setVehicleModel(String model) {
        this.vehicleModel = Jsoup.clean(model, Whitelist.simpleText());
    }

    public String getVehicleColour() {
        return this.vehicleColour;
    }
    public void setVehicleColour(String colour) {
        this.vehicleColour = Jsoup.clean(colour, Whitelist.simpleText());
    }

    public String getLicenseNo() {
        return this.licenseNo;
    }
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = Jsoup.clean(licenseNo, Whitelist.simpleText());
    }

    public LocalDate getVehicleCreated() { return this.vehicleCreated; }
    public void setVehicleCreated(LocalDate date) { this.vehicleCreated = date; }

    public LocalDate getVehicleDeleted() {
        if(vehicleDeleted != null) {
            return this.vehicleDeleted;
        }
        return null;
    }
    public void setVehicleDeleted(LocalDate date) {
        if (date != null) {
            this.vehicleDeleted = LocalDate.now();
        } else {
            this.vehicleDeleted = null;
        }
    }

    public Long getPersonId() { return this.person.getPersonId(); }
    public Person getPerson() { return this.person; }
    public void setPerson(Person x) { this.person = x; }
    /*Added*/
    public Long getNetworkId() { return this.network.getNetworkId(); }
    public Network getNetwork() { return this.network; }
    public void setNetwork(Network x) { this.network = x; }
}
