package com.example.foodmobile.model;

import java.io.Serializable;

public class Food implements Serializable {

    private String foodImage;
    private String foodId;
    private String foodName;
    private String foodDesc;
    private String foodPrice;
    private String foodCuisineCode;

    public Food() {
    }

    public Food(String foodImage, String foodId, String foodName, String foodDesc, String foodPrice, String foodCuisineCode) {
        this.foodImage = foodImage;
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.foodPrice = foodPrice;
        this.foodCuisineCode = foodCuisineCode;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodCuisineCode() {
        return foodCuisineCode;
    }

    public void setFoodCuisineCode(String foodCuisineCode) {
        this.foodCuisineCode = foodCuisineCode;
    }
}
