package com.example.it.firebasetest;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.it.firebasetest.Common.Common;
import com.example.it.firebasetest.Database.Database;
import com.example.it.firebasetest.Model.Order;
import com.example.it.firebasetest.Model.Request;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference requests;

    private Button payment;
    private Button back;

    private EditText name;
    private EditText phone;
    private EditText address;
    List<Order> cart = new ArrayList<>();

    int tempTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        payment = (Button) findViewById(R.id.txtPayment);
        back = (Button) findViewById(R.id.txtBack);

        name = (EditText) findViewById(R.id.txtHoVaTen);
        phone = (EditText) findViewById(R.id.txtSoDienThoai);
        address = (EditText) findViewById(R.id.txtDiaChi);

        name.setText(Common.currentUser.getName());
        phone.setText(Common.currentUser.getPhone());
        address.setText(Common.currentUser.getAddress());

        Intent intentResult = getIntent();
        Bundle bundle = intentResult.getBundleExtra("data");
        String totalPrice = bundle.getString("totalPrice", "");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonEffect(view);
                String nameU = name.getText().toString().trim();
                String phoneU = phone.getText().toString().trim();
                String addressU = address.getText().toString().trim();


                database = FirebaseDatabase.getInstance();
                requests = database.getReference("request");

                Request request = new Request(
                        phoneU,
                        nameU,
                        addressU,
                        totalPrice,
                        new Database(getBaseContext()).getCarts()
//                        cart
                );
                    requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);


                    new Database(getBaseContext()).cleneCart();
                    Toast.makeText(CheckoutActivity.this,"Successfully Ordered!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CheckoutActivity.this,Home.class));
            }
        });
    }

    private void buttonEffect(View view) {

        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
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
        });
    }
}