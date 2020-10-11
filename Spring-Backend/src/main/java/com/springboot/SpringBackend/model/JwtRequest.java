package com.springboot.SpringBackend.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class JwtRequest implements Serializable {

    private static final long serialVersionUID = -3973028000035563180L;

    @NotBlank
    @Size(max = 50)
    private String username;
    @NotBlank
    @Size(max = 35)
    private String password;

    //public JwtRequest() { }

    public JwtRequest(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
