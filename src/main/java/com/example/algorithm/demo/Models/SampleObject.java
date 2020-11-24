package com.example.algorithm.demo.Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SampleObject {
    
    private String name;
    private int value;
    private String date;

    public SampleObject(String name, int value){
        this.name=name;
        this.value=value;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }   

    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    public String getDate() {
        return date;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
}
