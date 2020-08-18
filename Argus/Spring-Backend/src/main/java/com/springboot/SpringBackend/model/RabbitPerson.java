package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitPerson implements Serializable {
    private static final long serialVersionUID = 5587161172602265855L;

    private Long personId;
    private String type;
    private Boolean exists;
    private String imageStr;
    private Boolean features;

    public RabbitPerson() {}

    public RabbitPerson(@JsonProperty("personId") Long pid, @JsonProperty("type") String type,
                        @JsonProperty("exists") Boolean tf, @JsonProperty("imageStr") String img,
                        @JsonProperty("features") Boolean face) {
        this.personId = pid;
        this.type = type;
        this.exists = tf;
        this.imageStr = img;
        this.features = face;
    }

    public Long getPersonId() { return this.personId; }
    public void setPersonId(Long id) { this.personId = id; }

    public String getType() { return this.type; }
    public void setType(String listed) { this.imageStr = listed; }

    public Boolean getExists() { return this.exists; }
    public void setExists(Boolean tf) { this.exists = tf; }

    public String getImageStr() { return this.imageStr; }
    public void setImageStr(String img) { this.imageStr = img; }

    public Boolean getFeatures() { return this.features; }
    public void setFeatures(Boolean tf) { this.features = tf; }
}
