package com.springboot.SpringBackend.model;

public class SessionModel {

    private final String id;
    private final String role;
    private final String email;
    private Network network;

    public SessionModel() {
        this.id = "";
        this.role = "";
        this.email = "";
        this.network = null;
    }

    public SessionModel(String id, String role, String email, Network network) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.network = network;
    }

    public String getId(){
        return this.id;
    }
    public String getRole(){
        return this.role;
    }
    public String getEmail(){
        return this.email;
    }
    public Network getNetworkDetails(){
        return this.network;
    }
    /*Added*/
    public Long getNetworkId() { return this.network.getNetworkId(); }
    public Network getNetwork() { return this.network; }
    public void setNetwork(Network x) { this.network = x; }
}
