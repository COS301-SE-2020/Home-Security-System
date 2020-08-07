package com.springboot.SpringBackend.rabbit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitPerson implements Serializable {
    private static final long serialVersionUID = 5587161172602265855L;
    private String image;

    public RabbitPerson() {}

    public RabbitPerson(@JsonProperty("image") String img) {
        this.image = img;
    }

    public String getImage() { return this.image; }
    public void setImage(String img) { this.image = img; }
}
