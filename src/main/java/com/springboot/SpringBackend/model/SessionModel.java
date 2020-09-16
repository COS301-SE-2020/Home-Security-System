package com.springboot.SpringBackend.model;

public class SessionModel {

    private final String id;
    private final String email;
    private final String password;
    private final String cellphone;
    private final String role;

    public SessionModel(){
        this.id = "";
        this.email = "";
        this.password = "";
        this.role = "";
        this.cellphone = "";
    }

    public SessionModel(String id, String email, String password, String cellphone, String role){
        // System.out.print("Cell: " + cellphone);
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.cellphone = cellphone;
    }

    public String getId(){
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
    public String getCellphone(){ return this.cellphone; }


}
