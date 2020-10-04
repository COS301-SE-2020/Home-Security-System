package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitAlert implements Serializable {
    private static final long serialVersionUID = -7446583075938108797L;

    private Long personId;
    private String type;
    private String imageStr;
    private Long networkId;

    public RabbitAlert() {}

    public RabbitAlert(@JsonProperty("personId") Long id, @JsonProperty("type") String type,
                       @JsonProperty("imageStr") String img, @JsonProperty("networkId") Long netId) {
        this.personId = id;
        this.type = type;
        this.imageStr = img;
        this.networkId = netId;
    }

    public Long getPersonId() { return this.personId; }
    public void setPersonId(Long id) { this.personId = id; }

    public String getType() { return this.type; }
    public void setType(String listed) { this.imageStr = listed; }

    public String getImageStr() { return this.imageStr; }
    public void setImageStr(String img) { this.imageStr = img; }

    public Long getNetworkId() { return this.networkId; }
    public void setNetworkId(Long id) { this.networkId = id; }
}
