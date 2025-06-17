package com.example.leavemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class managerhomepage extends AppCompatActivity {

    RecyclerView recyclerview;
    EmployeeAdapter employeeAdapter;
    ArrayList<Employee> employeeList = new ArrayList<>();
    DatabaseReference db;
    ImageView imageView;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerhomepage);

        recyclerview = findViewById(R.id.showemployee);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseDatabase.getInstance().getReference().child("employee");
        imageView = findViewById(R.id.logout);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                startActivity(new Intent(getApplicationContext(),managerlogin.class));
                finish();
            }
        });


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Employee managers = dataSnapshot.getValue(Employee.class);
                    employeeList.add(managers);
                }
                employeeAdapter = new EmployeeAdapter(getApplicationContext(),employeeList);
                recyclerview.setAdapter(employeeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}