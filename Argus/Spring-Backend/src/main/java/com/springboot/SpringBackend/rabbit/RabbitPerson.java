package com.springboot.SpringBackend.rabbit;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitPerson implements Serializable {
    private static final long serialVersionUID = 5587161172602265855L;

    private Long personId;
    private String type;
    private String faceStr;
    private String imageStr;
    private Boolean exists;

    public RabbitPerson() {}

    public RabbitPerson(@JsonProperty("personId") Long pid, @JsonProperty("type") String type,
                        @JsonProperty("faceStr") String face, @JsonProperty("imageStr") String img,
                        @JsonProperty("exists") Boolean tf) {
        this.personId = pid;
        this.type = type;
        this.imageStr = img;
        this.faceStr = face;
        this.exists = tf;
    }

    public Long getPersonId() { return this.personId; }
    public void setPersonId(Long id) { this.personId = id; }

    public String getType() { return this.type; }
    public void setType(String listed) { this.imageStr = listed; }

    public String getFaceStr() { return this.faceStr; }
    public void setFaceStr(String str) { this.faceStr = str; }

    public String getImageStr() { return this.imageStr; }
    public void setImageStr(String img) { this.imageStr = img; }

    public Boolean getExists() { return this.exists; }
    public void setExists(Boolean tf) { this.exists = tf; }
}
