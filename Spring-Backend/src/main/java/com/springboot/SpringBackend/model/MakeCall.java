package com.springboot.SpringBackend.model;

import java.io.Serializable;

public class MakeCall implements Serializable {

    private static final long serialVersionUID = -7093402766019337240L;

    private static final String ACCOUNT_ID = "ACa4cf3e220eb464dd1f0ad7a00db8d577";
    private static final String TOKEN_ID = "520f7026e1471479fb06ad9dcf3ddc18";
    private static final String FROM = "+16162293769";
    private String to;

    public MakeCall() { }

    public MakeCall(String number) {
        this.to = number;
    }

    public String getAccountId() {
        return ACCOUNT_ID;
    }

    public String getTokenId() {
        return TOKEN_ID;
    }

    public String getFrom() {
        return FROM;
    }

    public String getTo() {
        return this.to;
    }
    public void setTo(String to) {
        this.to = to;
    }
}
