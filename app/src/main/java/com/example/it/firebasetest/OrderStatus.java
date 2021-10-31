package com.example.it.firebasetest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.it.firebasetest.Common.Common;
import com.example.it.firebasetest.Interface.ItemClickListener;
import com.example.it.firebasetest.Model.Category;
import com.example.it.firebasetest.Model.Food;
import com.example.it.firebasetest.Model.Request;
import com.example.it.firebasetest.ViewHolder.MenuViewHolder;
import com.example.it.firebasetest.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class OrderStatus extends AppCompatActivity {
    
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    
    FirebaseRecyclerAdapter adapter;
    
    FirebaseDatabase database;
    DatabaseReference requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("request");
        
        recyclerView = findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        
        loadOrders(Common.currentUser.getPhone());
    }

    private void loadOrders(String phone) {

        Query query = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("request")
                .orderByChild("phone")
                .equalTo(phone);

        FirebaseRecyclerOptions<Request> options =
                new FirebaseRecyclerOptions.Builder<Request>()
                        .setQuery(query, Request.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(options){


            @Override
            protected void onBindViewHolder(@NonNull OrderViewHolder holder, int position, @NonNull Request model) {
                holder.txtOrderId.setText(adapter.getRef(position).getKey());
                holder.txtOrderPhone.setText(model.getPhone());
                holder.txtOrderSttus.setText(convertCodeToStates(model.getStatus()));
                holder.txtOrderAddress.setText(model.getAddress());
                final Request clickItem = model;
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                    }
                });
            }


            @Override
            public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.order_layout, parent, false);

                return new OrderViewHolder(view);
            };

        };

        recyclerView.setAdapter(adapter);
    }

    private String convertCodeToStates(String status) {

            if ("ordered".equals(status)) {
                return "Order Placed";
            }else
                return "Pending Order";
    }




    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
