package com.example.leavemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminhomepage extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhomepage);

        button1.findViewById(R.id.addmanager);
        button2.findViewById(R.id.viewmanagers);
        button3.findViewById(R.id.addemployee);
        button4.findViewById(R.id.viewemployee);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminhomepage.this,addmanager.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(adminhomepage.this,viewmanager.class);
                startActivity(intention);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intented = new Intent(adminhomepage.this,addemployee.class);
                startActivity(intented);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intence = new Intent(adminhomepage.this,viewemployee.class);
                startActivity(intence);
            }
        });

    }
}