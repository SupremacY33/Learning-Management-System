package com.example.leavemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class employeehomepage extends AppCompatActivity {

    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeehomepage);

        button1.findViewById(R.id.upcoming);
        button2.findViewById(R.id.leave);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent upcoming = new Intent(employeehomepage.this,upcoming.class);
                startActivity(upcoming);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent leave = new Intent(employeehomepage.this , leave.class);
                startActivity(leave);
            }
        });
    }
}