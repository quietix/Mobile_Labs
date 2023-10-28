package com.example.lab2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class MainActivity extends AppCompatActivity {

    // Task2
    private TextView textViewToMove;
    private ToggleButton toggleButton;

    // Task3
    private Button addItemButton;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private EditText textInput;

    // Task3_1
    private LinearLayout itemsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         Task2();
        Task3();
//        Task3_1();
    }

    private void Task3_1(){
        setContentView(R.layout.layout_2_1);

        addItemButton = findViewById(R.id.add_item_button);
        textInput = findViewById(R.id.input);

        addItemButton.setOnClickListener(view -> {
            TextView newItem = new TextView(this);
            itemsContainer = findViewById(R.id.items_container);

            Drawable drawable = getResources().getDrawable(R.drawable.border);
            newItem.setBackground(drawable);
            newItem.setPadding(10,10,10,10);

            String text = String.valueOf(textInput.getText());

            if (!text.isEmpty()){
                newItem.setText(text);
                itemsContainer.addView(newItem);
                textInput.setText("");
            }
        });
    }

    private void Task3(){
        setContentView(R.layout.layout_2);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        addItemButton = findViewById(R.id.add_item_button);
        spinner = findViewById(R.id.spinner);
        textInput = findViewById(R.id.input);

        addItemButton.setOnClickListener(view -> {
            String text = String.valueOf(textInput.getText());

            if (!text.isEmpty()){
                textInput.setText("");
                adapter.add(text);
                spinner.setAdapter(adapter);
            }
        });
    }

    private void Task2(){
        setContentView(R.layout.layout_1);

        toggleButton = findViewById(R.id.toggleButton);
        textViewToMove = findViewById(R.id.text_box_to_move);

        toggleButton.setOnClickListener(view -> {
            if (toggleButton.isChecked()) {
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
            else {
                ConstraintLayout parentLayout = (ConstraintLayout) textViewToMove.getParent();

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(parentLayout);

                constraintSet.clear(R.id.text_box_to_move, ConstraintSet.TOP);
                constraintSet.clear(R.id.text_box_to_move, ConstraintSet.LEFT);
                constraintSet.clear(R.id.text_box_to_move, ConstraintSet.RIGHT);
                constraintSet.clear(R.id.text_box_to_move, ConstraintSet.BOTTOM);

                constraintSet.connect(R.id.text_box_to_move, ConstraintSet.TOP, R.id.main_layout, ConstraintSet.TOP);
                constraintSet.connect(R.id.text_box_to_move, ConstraintSet.RIGHT, R.id.main_layout, ConstraintSet.RIGHT);

                constraintSet.applyTo(parentLayout);
            }
        });
    }
}