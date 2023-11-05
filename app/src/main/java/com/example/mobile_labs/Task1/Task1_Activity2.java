package com.example.mobile_labs.Task1;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mobile_labs.R;

public class Task1_Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_2);
        generateCountriesList();
    }

    public void showScreen1(View view, String selectedCountry) {
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_TEXT, selectedCountry);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }


    private void generateCountriesList() {
        String[] countries = {
                "Австрія",
                "Албанія",
                "Андорра",
                "Бельгія",
                "Болгарія",
                "Боснія та Герцеговина",
                "Велика Британія",
                "Греція",
                "Україна"
        };

        ListView countriesList = findViewById(R.id.countries_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, countries);
        countriesList.setAdapter(adapter);

        makeListItemsTouchable(countriesList);
    }

    private void makeListItemsTouchable(ListView countriesList) {
        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = (String) parent.getItemAtPosition(position);
                showScreen1(view, selectedCountry);
            }
        });
    }
}