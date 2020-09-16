package com.springboot.SpringBackend.model;

public class SessionModel {

    private final String id;
    private final String role;
    private final String email;

    public SessionModel() {
        this.id = "";
        this.role = "";
        this.email = "";
    }

    public SessionModel(String id, String role, String email) {
        this.id = id;
        this.role = role;
        this.email = email;
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
}
