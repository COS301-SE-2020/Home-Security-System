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

    public RabbitPerson() {}

    public RabbitPerson(@JsonProperty("personId") Long pid, @JsonProperty("type") String type,
                        @JsonProperty("exists") Boolean tf, @JsonProperty("imageStr") String img) {
        this.personId = pid;
        this.type = type;
        this.exists = tf;
        this.imageStr = img;
    }

    public Long getPersonId() { return this.personId; }
    public void setPersonId(Long id) { this.personId = id; }

    public String getType() { return this.type; }
    public void setType(String listed) { this.imageStr = listed; }

    public String getImageStr() { return this.imageStr; }
    public void setImageStr(String img) { this.imageStr = img; }

    public Boolean getExists() { return this.exists; }
    public void setExists(Boolean tf) { this.exists = tf; }
}
