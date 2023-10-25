package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class MainActivity extends AppCompatActivity {

    private TextView textViewToMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_1);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        textViewToMove = findViewById(R.id.text_box_to_move);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout parentLayout = (ConstraintLayout) textViewToMove.getParent();

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(parentLayout);

                constraintSet.clear(R.id.text_box_to_move, ConstraintSet.TOP);
                constraintSet.clear(R.id.text_box_to_move, ConstraintSet.LEFT);
                constraintSet.clear(R.id.text_box_to_move, ConstraintSet.RIGHT);
                constraintSet.clear(R.id.text_box_to_move, ConstraintSet.BOTTOM);

                constraintSet.connect(R.id.text_box_to_move, ConstraintSet.BOTTOM, R.id.main_layout, ConstraintSet.BOTTOM);
                constraintSet.connect(R.id.text_box_to_move, ConstraintSet.RIGHT, R.id.main_layout, ConstraintSet.RIGHT);

                constraintSet.applyTo(parentLayout);
            }
        });
    }
}