package com.springboot.SpringBackend.model;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "camera")
public class Camera implements Serializable {
    private static final long serialVersionUID = -1651309702519570738L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "camera_id", nullable = false)
    private Long id;

    @Column(name = "serverurl", nullable = false)
    private String serverURL;

    @ManyToOne
    @JoinColumn(name="network_id", nullable = false)
    private Network network;

    public Camera() { }

    public Camera(String url, Network net) {
        this.serverURL = url;
    }

    public Long getCameraId() {
        return this.id;
    }
    public void setCameraId(Long id) {
        this.id = id;
    }

    public String getServerURL() {
        return this.serverURL;
    }
    public void setServerURL(String url) {
        this.serverURL = Jsoup.clean(url, Whitelist.simpleText());
    }

    public Long getNetworkId() { return this.network.getNetworkId(); }
    public Network getNetwork() { return this.network; }
    public void setNetwork(Network x) { this.network = x; }
}
