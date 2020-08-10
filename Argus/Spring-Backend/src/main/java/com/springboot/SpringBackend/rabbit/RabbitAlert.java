package com.springboot.SpringBackend.rabbit;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = RabbitAlert.class)
public class RabbitAlert implements Serializable {
    private static final long serialVersionUID = -7446583075938108797L;

    private String imageStr;
    private Long pid;

    public RabbitAlert() {}

    public RabbitAlert(@JsonProperty("imageStr") String img, @JsonProperty("pid") Long id) {
        this.imageStr = img;
        this.pid = id;
    }

    public String getImageStr() { return this.imageStr; }
    public void setImageStr(String img) { this.imageStr = img; }

    public Long getPid() { return this.pid; }
    public void setPid(Long id) { this.pid = id; }
}
