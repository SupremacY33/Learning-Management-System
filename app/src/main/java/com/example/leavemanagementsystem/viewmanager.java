package com.example.leavemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewmanager extends AppCompatActivity {

        private RecyclerView mRecyclerView;
        private ManagerAdapter mAdapter;
        private DatabaseReference mDatabaseRef;
        private List<Manager> mManagerList;

        Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmanager);


        button1.findViewById(R.id.goingback);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentss = new Intent(viewmanager.this,adminhomepage.class);
                startActivity(intentss);
            }
        });

        mRecyclerView = findViewById(R.id.showmanager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mManagerList = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("managers");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mManagerList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Manager manager = postSnapshot.getValue(Manager.class);
                    mManagerList.add(manager);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(viewmanager.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter = new ManagerAdapter(viewmanager.this, mManagerList);
        mRecyclerView.setAdapter(mAdapter);
    }
}