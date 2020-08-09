package com.springboot.SpringBackend.rabbit;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = RabbitPerson.class)
public class RabbitPerson implements Serializable {
    private static final long serialVersionUID = 5587161172602265855L;

    private String faceStr;
    private String imageStr;

    public RabbitPerson() {}

    public RabbitPerson(@JsonProperty("imageStr") String img, @JsonProperty("faceStr") String face) {
        this.imageStr = img;
        this.faceStr = face;
    }

    public String getImageStr() { return this.imageStr; }
    public void setImageStr(String img) { this.imageStr = img; }

    public String getFaceStr() { return this.faceStr; }
    public void setFaceStr(String str) { this.faceStr = str; }
}
