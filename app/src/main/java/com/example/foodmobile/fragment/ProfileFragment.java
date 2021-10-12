package com.example.foodmobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodmobile.LoginActivity;
import com.example.foodmobile.R;
import com.example.foodmobile.model.Customer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ProfileFragment extends Fragment {

    private Button signout;
    private TextView hello;
    private ImageView avatar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,null);
        getActivity().setTitle("Profile");

        avatar = v.findViewById(R.id.avatar);
        avatar.setImageResource(R.drawable.avatar);

        hello = v.findViewById(R.id.txtHelloUser);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref= database.getReference("Customer/P9BqluWAKPWnfCvLsAHHAG59hly2");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Customer customer = snapshot.getValue(Customer.class);
                String cc = customer.getFirstName();
                hello.setText(cc);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        signout = v.findViewById(R.id.btnSignout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });


        return v;
    }
}
