package com.example.mobile_labs;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import kotlin.sequences.Sequence;

public class MainActivity extends AppCompatActivity {

    Toast currentToast;
    Button button;
    PopupMenu popupMenu;
    Boolean needReverseChecker = false;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_1);

        button = findViewById(R.id.popupMenuButton);
        fillPopupMenu(generateData(needReverseChecker));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu();
            }
        });

        textView = (TextView) findViewById(R.id.textView);
        registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // you can set menu header with title icon etc
        menu.setHeaderTitle("Choose a color");
        // add menu items
        menu.add(0, v.getId(), 0, "Yellow");
        menu.add(0, v.getId(), 0, "Gray");
        menu.add(0, v.getId(), 0, "Cyan");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Yellow") {
            textView.setTextColor(Color.YELLOW);
        } else if (item.getTitle() == "Gray") {
            textView.setTextColor(Color.GRAY);
        } else if (item.getTitle() == "Cyan") {
            textView.setTextColor(Color.CYAN);
        }
        return true;
    }

    private void fillPopupMenu(List<String> data) {
        popupMenu = new PopupMenu(getApplicationContext(), button);
        Menu menu = popupMenu.getMenu();

        for (int i = 0; i < data.size(); i++) {
            menu.add(data.get(i));
        }
    }

    private List<String> generateData(Boolean needReverse) {
        String[] itemsArray = new String[] {
            "Dog", "Cat", "Fox", "Aligator"
        };
        List<String> itemsList = Arrays.asList(itemsArray);

        Collections.sort(itemsList);
        if (needReverse) {
             Collections.reverse(itemsList);
        }

        return itemsList;
    }

    private void showPopupMenu() {
        Menu menu = popupMenu.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(@NonNull MenuItem item) {
                    needReverseChecker = !needReverseChecker;
                    fillPopupMenu(generateData(needReverseChecker));
                    showToast("List items order was changed");
                    return false;
                }
            });
        }

        popupMenu.show();
    }

    private void showToast(String message) {
        if (currentToast != null) {
            currentToast.cancel();
        }
        currentToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        currentToast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String message = Objects.requireNonNull(item.getTitle()).toString();
        if (currentToast != null) {
            currentToast.cancel();
        }
        currentToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        currentToast.show();
        return true;
    }
}
