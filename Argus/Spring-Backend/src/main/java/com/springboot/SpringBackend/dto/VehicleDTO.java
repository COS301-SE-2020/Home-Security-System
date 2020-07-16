package com.springboot.SpringBackend.dto;

import com.springboot.SpringBackend.model.Image;
import com.springboot.SpringBackend.model.PersonVehicle;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VehicleDTO implements Serializable {
    public enum vehicleType {
        White, Grey, Black;
    }

    private static final long serialVersionUID = 1L;
    private Long id;
    private vehicleType listed;
    private String licenseNo;
    private String created;
    private String deletionDate;

    private Image vehicleImg;
    private List<PersonVehicle> personList;

    public VehicleDTO() { }

    public VehicleDTO(String listed, String licenseNo, Image img) {
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

    public VehicleDTO(String licenseNo, Image img) {
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

    public Long getPhotoById() {
        return this.vehicleImg.getImageId();
    }
    public Image getPhoto() {
        return this.vehicleImg;
    }
    public void setPhoto(Image id) {
        this.vehicleImg = id;
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
