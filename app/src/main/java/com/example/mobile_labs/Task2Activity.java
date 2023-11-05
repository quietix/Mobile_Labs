package com.example.mobile_labs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Task2Activity extends AppCompatActivity {
    Button exitButton;
    Button calculateButton;
    EditText limit1;
    EditText limit2;
    EditText step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);

        exitButton = findViewById(R.id.exitButton);
        calculateButton = findViewById(R.id.calculateButton);
        limit1 = findViewById(R.id.limit1TextBox);
        limit2 = findViewById(R.id.limit2TextBox);
        step = findViewById(R.id.stepTextBox);

        exitButton.setOnClickListener(v -> {
            finish();
        });

        calculateButton.setOnClickListener(v -> {
            if (readyToCalculate()){
                // Preparing array to transfer input data to Task2Activity_2
                String[] inputs = new String[] {
                        limit1.getText().toString(),
                        limit2.getText().toString(),
                        step.getText().toString()
                };
                Intent intent = new Intent(this, Task2Activity_2.class);
                intent.putExtra("inputData", inputs);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Some of input numbers are wrong. Try again.", Toast.LENGTH_LONG).show();
            }
        });
    }

    boolean readyToCalculate() {
        // Get the input values as strings
        String lim1Str = limit1.getText().toString();
        String lim2Str = limit2.getText().toString();
        String stepStr = step.getText().toString();

        // Check if any of the input fields are empty
        if (lim1Str.isEmpty() || lim2Str.isEmpty() || stepStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all input fields.", Toast.LENGTH_LONG).show();
            return false;
        }

        try {
            double lim1 = Double.parseDouble(lim1Str);
            double lim2 = Double.parseDouble(lim2Str);
            double st = Double.parseDouble(stepStr);

            if (lim1 < lim2 && st > 0) {
                return true;
            } else {
                resetFields();
                Toast.makeText(this, "Invalid input values. Make sure limit1 < limit2 and step > 0.", Toast.LENGTH_LONG).show();
                return false;
            }
        } catch (NumberFormatException e) {
            resetFields();
            Toast.makeText(this, "Invalid number format. Please enter valid numbers.", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    void resetFields(){
        limit1.setText("");
        limit2.setText("");
        step.setText("");
    }
}