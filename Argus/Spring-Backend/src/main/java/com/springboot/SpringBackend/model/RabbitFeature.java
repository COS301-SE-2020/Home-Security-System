package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitFeature implements Serializable {
    private static final long serialVersionUID = 7824393394379772810L;

    private Long personId;
    private String faceStr;

    public RabbitFeature() {}

    public RabbitFeature(@JsonProperty("personId") Long pid, @JsonProperty("faceStr") String face) {
        this.personId = pid;
        this.faceStr = face;
    }

    public Long getPersonId() { return this.personId; }
    public void setPersonId(Long id) { this.personId = id; }

    public String getFaceStr() { return this.faceStr; }
    public void setFaceStr(String str) { this.faceStr = str; }
}
