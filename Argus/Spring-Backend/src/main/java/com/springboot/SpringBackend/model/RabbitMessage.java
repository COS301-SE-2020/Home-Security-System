package com.springboot.SpringBackend.model;

public class RabbitMessage {
    private String message;

    public RabbitMessage() {}

    public RabbitMessage(String msg) {
        this.message = msg;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
