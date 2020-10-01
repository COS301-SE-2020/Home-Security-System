package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "network")
public class Network implements Serializable {
    private static final long serialVersionUID = 7192788037622789092L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "network_id", nullable = false)
    private Long id;

    @Column(name = "netname", nullable = false)
    private String netName;

    @OneToMany(mappedBy="network", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Camera> camList = new ArrayList<>();

    public Network() { }

    public Network(String name) {
        this.netName = name;
    }

    public Long getNetworkId() {
        return this.id;
    }
    public void setNetworkId(Long id) {
        this.id = id;
    }

    public String getNetName() {
        return this.netName;
    }
    public void setNetName(String name) {
        this.netName = Jsoup.clean(name, Whitelist.simpleText());
    }

    public List<Camera> getCamList() { return this.camList; }
    public void setCamList(List<Camera> list) { this.camList = list; }
}
