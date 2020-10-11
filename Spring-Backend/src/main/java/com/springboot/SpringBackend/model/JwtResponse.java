package com.springboot.SpringBackend.model;

//import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
//import java.util.Collection;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1819193432925865364L;

    private String token;
    //private String tokenType = "Bearer";
    //private String username;

    public JwtResponse(String accessToken) {
        this.token = accessToken;
    }

    /*public JwtResponse(String token, String username) {
        this.accessToken = token;
        this.username = username;
    }*/

    public String getToken() {
        return this.token;
    }
    public void setToken(String accessToken) {
        this.token = accessToken;
    }

    /*public String getTokenType() {
        return this.tokenType;
    }
    public void setTokenType(String type) {
        this.tokenType = type;
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }*/
}
