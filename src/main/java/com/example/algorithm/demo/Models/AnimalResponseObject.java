package com.example.algorithm.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AnimalResponseObject {
    
    private String text;
    private String user;

    public String getText() {
        return text;
    }
    public String getUser() {
        return user;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setUser(String user) {
        this.user = user;
    }

}
