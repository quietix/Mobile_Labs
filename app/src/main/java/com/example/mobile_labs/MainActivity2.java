package com.example.mobile_labs;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_2);

        button = findViewById(R.id.popupMenuButton2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAudioFilesAsMenu();
            }
        });
    }

    private void displayAudioFilesAsMenu() {
        List<String> audioFiles = getAudioFiles();

        PopupMenu popupMenu = new PopupMenu(this, button);

        for (int i = 0; i < audioFiles.size(); i++) {
            popupMenu.getMenu().add(Menu.NONE, i, Menu.NONE, audioFiles.get(i));
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int index = menuItem.getItemId();
                String selectedAudioFile = audioFiles.get(index);
                showToast("Selected Audio File:\n" + selectedAudioFile);
                return true;
            }
        });

        popupMenu.show();
    }

    private List<String> getAudioFiles() {
        List<String> audioFiles = new ArrayList<>();

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA
        };

        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                String fileInfo = "Title: " + title + "\nArtist: " + artist + "\nPath: " + path;

                audioFiles.add(fileInfo);
            }
            cursor.close();
        }

        return audioFiles;
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
