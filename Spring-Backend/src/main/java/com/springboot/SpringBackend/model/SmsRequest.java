package com.springboot.SpringBackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class SmsRequest {
    @NotBlank
    private final String numb;
    @NotBlank
    private final String name;
    @NotBlank
    private final String date;
    @NotBlank
    private final String time;

    public SmsRequest(@JsonProperty("phoneNumb") String num, @JsonProperty("name") String name,
                      @JsonProperty("date") String date, @JsonProperty("time") String time) {
        this.numb = num;
        this.name = name;
        this.time = time;
        this.date = date;
    }

    public String getNumb() {
        return numb;
    }
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }

}
