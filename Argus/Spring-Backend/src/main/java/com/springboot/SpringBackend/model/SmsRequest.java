package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class SmsRequest {
    @NotBlank
    private static String numb;

    public SmsRequest(@JsonProperty("phoneNumb") String numb) {
        SmsRequest.numb = numb;
    }

    public static String getNumb() {
        return numb;
    }

    @Override
    public String toString() {
        return "smsRequest{" +
                "numb='" + numb + '\'' +
                '}';
    }
}
