package com.springboot.SpringBackend.rabbit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitAlert implements Serializable {
    private static final long serialVersionUID = -7446583075938108797L;

    private String image;
    private int pid;

    public RabbitAlert() {}

    public RabbitAlert(@JsonProperty("image") String img, @JsonProperty("pid") int id) {
        this.image = img;
        this.pid = id;
    }

    public String getImage() { return this.image; }
    public void setImage(String img) { this.image = img; }

    public int getPid() { return this.pid; }
    public void setPid(int id) { this.pid = id; }
}
