package com.example.leavemanagementsystem;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class upcoming extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);
        CalendarView calendarView = findViewById(R.id.calendarView);

        // Set the listener for date changes
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                // Handle the selected date here
                Toast.makeText(getApplicationContext(), "Selected date: " + year + "-" + month + "-" + dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        });

    }
}