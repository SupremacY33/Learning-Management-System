package com.example.leavemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class leave extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextFromDate;
    private EditText editTextToDate;
    private EditText editTextReason;
    private Button buttonSubmit;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        editTextName = findViewById(R.id.editTextName);
        editTextFromDate = findViewById(R.id.editTextFromDate);
        editTextToDate = findViewById(R.id.editTextToDate);
        editTextReason = findViewById(R.id.editTextReason);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String fromDate = editTextFromDate.getText().toString();
                String toDate = editTextToDate.getText().toString();
                String reason = editTextReason.getText().toString();

                leaves leave = new leaves(name, fromDate, toDate, reason);
                mDatabase.child("leaves").push().setValue(leave);
            }
        });
    }
}