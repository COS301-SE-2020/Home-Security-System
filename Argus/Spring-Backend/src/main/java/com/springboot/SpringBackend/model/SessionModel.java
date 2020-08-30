package com.springboot.SpringBackend.model;

public class SessionModel {

    private final String id;
    private final String email;
    private final String password;
    private final String cellphone;
    private final String role;
    /*private final String cellNo;*/
    /*private final Boolean promptSms;*/

    public SessionModel(){
        this.id = "";
        this.email = "";
        this.password = "";
        this.role = "";
        this.cellphone = "";
        /*this.cellNo = "";*/
        /*this.promptSms = false;*/
    }
    public SessionModel(String id, String email, String password, String cellphone, String role){
        System.out.println("cellNumb: " + cellphone);
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.cellphone = cellphone;
        /*this.cellNo = cellNo;*/
        /*this.promptSms = sms;*/
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
    public String getCell(){ return this.cellphone; }
    /*public Boolean getPromptSms(){return this.promptSms;}*/

}
