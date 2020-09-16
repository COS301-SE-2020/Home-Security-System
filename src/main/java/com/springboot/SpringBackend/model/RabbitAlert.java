package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitAlert implements Serializable {
    private static final long serialVersionUID = -7446583075938108797L;

    private Long personId;
    private String type;
    private String imageStr;

    public RabbitAlert() {}

    public RabbitAlert(@JsonProperty("personId") Long id, @JsonProperty("type") String type, @JsonProperty("imageStr") String img) {
        this.personId = id;
        this.type = type;
        this.imageStr = img;
    }

    public Long getPersonId() { return this.personId; }
    public void setPersonId(Long id) { this.personId = id; }

    public String getType() { return this.type; }
    public void setType(String listed) { this.imageStr = listed; }

    public String getImageStr() { return this.imageStr; }
    public void setImageStr(String img) { this.imageStr = img; }
}
