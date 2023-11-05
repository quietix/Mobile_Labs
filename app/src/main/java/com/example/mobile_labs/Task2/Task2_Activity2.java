package com.example.mobile_labs.Task2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

import com.example.mobile_labs.R;

public class Task2_Activity2 extends AppCompatActivity {
    Button homeButton;
    GridView gvResults;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2_2);

        homeButton = findViewById(R.id.homeButton);

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Task2_Activity1.class);
            startActivity(intent);
        });

        try {
            fillGridView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void fillGridView() throws Exception {
        String[] receivedData = getIntent().getStringArrayExtra("inputData");

        if (receivedData != null) {
            List<String> data = new ArrayList<>();
            data.add("X");
            data.add("Y");

            fillListWithResults(data,
                    Double.parseDouble(receivedData[0]),
                    Double.parseDouble(receivedData[1]),
                    Double.parseDouble(receivedData[2]));

            adapter = new ArrayAdapter<String>(this, R.layout.task2_grid_item, R.id.tvText, data);
            gvResults = (GridView) findViewById(R.id.gv_func_results);
            gvResults.setAdapter(adapter);
            gvResults.setNumColumns(2);
        }
        else {
            throw new Exception("Received null data. Cannot process calculations");
        }
    }

    void fillListWithResults(List<String> data, double startX, double endX, double step){
        DecimalFormat df = new DecimalFormat("0.000");
        double currentX = startX;
        while (currentX <= endX) {
            double y = y(currentX);
            data.add(df.format(currentX));
            data.add(df.format(y));
            currentX += step;
        }
    }

    double y(double x){
        return 1 + pow(cos(sqrt(x)), 2);
    }
}