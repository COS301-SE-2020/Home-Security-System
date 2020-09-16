package com.springboot.SpringBackend.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twillio")
public class TwillioConfig {

    private String accountId = "ACa4cf3e220eb464dd1f0ad7a00db8d577";
    private String token = "520f7026e1471479fb06ad9dcf3ddc18";
    private String number = "+16162293769";

    public TwillioConfig(){}

    public void setAccount (String id){
        this.accountId = id;
    }
    public void setToken (String tkn){
        this.token = tkn;
    }
    public void setNum (String n){
        this.number = n;
    }
    public String getAccountId(){
        return this.accountId;
    }
    public String getToken(){
        return this.token;
    }
    public String getNumber(){
        return this.number;
    }

}
