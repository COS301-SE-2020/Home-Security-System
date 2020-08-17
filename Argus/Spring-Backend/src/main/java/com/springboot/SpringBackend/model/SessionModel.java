package com.springboot.SpringBackend.model;

import com.springboot.SpringBackend.service.SessionService;

public class SessionModel {

    private final String id;
    private final String email;
    private final String password;
    private final String role;

    public SessionModel(){
        this.id = "";
        this.email = "";
        this.password = "";
        this.role = "";
    }
    public SessionModel(String id, String email, String password, String role){
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public String getID(){
        return this.id;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRole(){
        return this.role;
    }

}
