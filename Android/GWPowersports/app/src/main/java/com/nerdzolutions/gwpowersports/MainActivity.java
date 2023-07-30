package com.nerdzolutions.gwpowersports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CardView scanToShelf;
    ArrayList<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanToShelf = findViewById(R.id.card_view_scan_to_shelf);

        scanToShelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startersActivityIntent = new Intent(MainActivity.this, ScanToShelfActivity.class);

                startActivity(startersActivityIntent);
            }
        });
    }
}