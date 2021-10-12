package com.example.foodmobile.helper;

import android.util.Log;


import com.example.foodmobile.model.FoodCartQtyHelper;

import java.util.ArrayList;

public class FoodCart {
    private static final String TAG = "FoodCart";

    private static FoodCart foodCart = null;

    public ArrayList<FoodCartQtyHelper> foodCartList;

    public FoodCart() {
        foodCartList = new ArrayList<FoodCartQtyHelper>();
    }

    public static FoodCart getInstance(){

        if (foodCart == null){
            foodCart = new FoodCart();
        }

        return foodCart;
    }

    public static void displayCart(){

        FoodCart foodCart = FoodCart.getInstance();

        for (int i = 0; i < foodCart.foodCartList.size(); i++){
            Log.d(TAG, foodCart.foodCartList.get(i).getFood().getFoodName()+", #Qty: "+foodCart.foodCartList.get(i).getQuantity());
        }
    }
}
