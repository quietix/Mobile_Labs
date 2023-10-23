package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class MainActivity extends AppCompatActivity {

    private TextView textViewToRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_1);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        textViewToRemove = findViewById(R.id.text_box_to_move);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout parentLayout = (ConstraintLayout) textViewToRemove.getParent();
                parentLayout.removeView(textViewToRemove);

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(parentLayout);

                TextView newTextView = CreateTextView();
                parentLayout.addView(newTextView);

                constraintSet.connect(newTextView.getId(), ConstraintSet.END, parentLayout.getId(), ConstraintSet.END);
                constraintSet.connect(newTextView.getId(), ConstraintSet.BOTTOM, parentLayout.getId(), ConstraintSet.BOTTOM);

                constraintSet.applyTo(parentLayout);
            }
        });
    }

    private TextView CreateTextView(){
        TextView newTextView = new TextView(this);
        newTextView.setText("Text1");
        newTextView.setId(View.generateViewId());
        return newTextView;
    }
}