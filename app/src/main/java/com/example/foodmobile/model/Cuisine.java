package com.example.foodmobile.model;

import java.io.Serializable;

public class Cuisine implements Serializable {
    private String cuisineCode;
    private String cuisineName;

    public Cuisine() {
    }

    public Cuisine(String cuisineCode, String cuisineName) {
        this.cuisineCode = cuisineCode;
        this.cuisineName = cuisineName;
    }

    public String getCuisineCode() {
        return cuisineCode;
    }

    public void setCuisineCode(String cuisineCode) {
        this.cuisineCode = cuisineCode;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }
}
