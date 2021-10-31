package com.example.it.firebasetest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it.firebasetest.Common.Common;
import com.example.it.firebasetest.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText password,phoneNum;
    Button btnSignIn, btnSignUp;

    DatabaseReference table_user;

    private static boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                v.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP: {
                v.getBackground().clearColorFilter();
                v.invalidate();
                break;
            }
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNum = findViewById(R.id.txtPhoneNumber);
        password = findViewById(R.id.txtPssword);
        btnSignIn = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        table_user = database.getReference("user");


        btnSignUp.setOnClickListener(view -> {

            buttonEffect(view);

            Intent signUp = new Intent(MainActivity.this, SignUp.class);
            startActivity(signUp);
        });

        btnSignIn.setOnClickListener(view -> {

            buttonEffect(view);
            final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setMessage("Loading...");
            mDialog.show();

            table_user.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    mDialog.dismiss();
                    if(dataSnapshot.child(phoneNum.getText().toString()).exists()){
                        String phone = phoneNum.getText().toString();
                        User user = dataSnapshot.child(phone).getValue(User.class);
                        user.setPhone(phone);
                        if(user.getPassword().equals(password.getText().toString())){
                            Intent homeIntent = new Intent(MainActivity.this, Home.class);
                            Common.currentUser = user;

                            startActivity(homeIntent);
                            finish();
                        }

                        else {
                            Toast.makeText(MainActivity.this,"Signin failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this,"User not exist",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        });

    }

    private void buttonEffect(View view) {

            view.setOnTouchListener(MainActivity::onTouch);
        }

}


