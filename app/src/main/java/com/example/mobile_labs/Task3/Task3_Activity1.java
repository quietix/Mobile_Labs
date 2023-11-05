package com.example.mobile_labs.Task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobile_labs.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Task3_Activity1 extends AppCompatActivity {
    Button showCoordinates;
    Button makeCall;
    Button showWeather;
    Button showTime;
    Button showDate;
    Toast currentToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3_1);
        initElems();
        setEvents();
    }

    void setEvents() {
        showCoordinates.setOnClickListener(v -> {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q="));

            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        });

        makeCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            startActivity(intent);
        });

        showWeather.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri weatherUri = Uri.parse("https://www.weather.com");
            intent.setData(weatherUri);
            startActivity(intent);
        });

        showTime.setOnClickListener(v -> {
            long currentTimeMillis = System.currentTimeMillis();
            String currentTime = formatTime(currentTimeMillis);
            String message = String.format(Locale.getDefault(), currentTime);

            if (currentToast != null) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            currentToast.show();
        });

        showDate.setOnClickListener(v -> {
            long currentTimeMillis = System.currentTimeMillis();
            String currentDate = formatDate(currentTimeMillis);
            String message = String.format(Locale.getDefault(), currentDate);

            if (currentToast != null) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            currentToast.show();
        });
    }

    private String formatDate(long timeMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date(timeMillis);
        return sdf.format(date);
    }

    String formatTime(long timeMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = new Date(timeMillis);
        return sdf.format(date);
    }

    void initElems() {
        showCoordinates = findViewById(R.id.showCoordinates);
        makeCall = findViewById(R.id.makeCall);
        showWeather = findViewById(R.id.showWeather);
        showTime = findViewById(R.id.showTime);
        showDate = findViewById(R.id.showDate);
    }
}