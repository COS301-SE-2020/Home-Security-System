package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitVehicle implements Serializable {
    private static final long serialVersionUID = -4391455224866758922L;

    private Long vehicleId;
    private String tempId;
    private String type;
    private Boolean exists;
    private String imageStr;
    private Boolean plates;

    public RabbitVehicle() {}

    public RabbitVehicle(@JsonProperty("personId") Long vid, @JsonProperty("tempId") String tid,
                        @JsonProperty("type") String type, @JsonProperty("exists") Boolean tf,
                        @JsonProperty("imageStr") String img, @JsonProperty("features") Boolean num) {
        this.vehicleId = vid;
        this.tempId = tid;
        this.type = type;
        this.exists = tf;
        this.imageStr = img;
        this.plates = num;
    }

    public Long getVehicleId() { return this.vehicleId; }
    public void setVehicleId(Long id) { this.vehicleId = id; }

    public String getTempId() { return this.tempId; }
    public void setTempId(String tid) { this.tempId = tid; }

    public String getType() { return this.type; }
    public void setType(String listed) { this.imageStr = listed; }

    public Boolean getExists() { return this.exists; }
    public void setExists(Boolean tf) { this.exists = tf; }

    public String getImageStr() { return this.imageStr; }
    public void setImageStr(String img) { this.imageStr = img; }

    public Boolean getPlates() { return this.plates; }
    public void setPlates(Boolean tf) { this.plates = tf; }
}
