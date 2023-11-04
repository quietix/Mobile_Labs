package com.example.mobile_labs;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    public TextView selectedCountryTextView;
    private Button showCountriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_1);

        showCountriesButton = findViewById(R.id.show_countries_list_button);
        selectedCountryTextView = findViewById(R.id.selected_country_text_view);

        showCountriesButton.setOnClickListener(this::showScreen2);
    }

    public void showScreen2(View view){
        Intent intent1 = new Intent(getApplicationContext(), MainActivity2.class);
        startActivityForResult(intent1, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String selectedCountry = data.getStringExtra(Intent.EXTRA_TEXT);
            selectedCountryTextView.setText(selectedCountry);
            Toast.makeText(getApplicationContext(), selectedCountry, Toast.LENGTH_SHORT).show();
        }
    }
}