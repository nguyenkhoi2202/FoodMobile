package com.example.foodmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodmobile.fragment.CartFragment;
import com.example.foodmobile.fragment.HistoryFragment;
import com.example.foodmobile.fragment.HomeFragment;
import com.example.foodmobile.fragment.ProfileFragment;
import com.example.foodmobile.helper.FoodCart;
import com.example.foodmobile.model.Cuisine;
import com.example.foodmobile.model.Food;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class FoodMenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.Home:
                fragment = new HomeFragment();
                break;
            case R.id.Cart:
                fragment = new CartFragment();
                break;
            case R.id.History:
                fragment = new HistoryFragment();
                break;
            case R.id.Profile:
                fragment = new ProfileFragment();
                break;
        }
        return loadfragment(fragment);
    }

    private boolean loadfragment(Fragment fragment) {
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_customer,fragment).commit();
        return true;
        }
        return false;
    }


}