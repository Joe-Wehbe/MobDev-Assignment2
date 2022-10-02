package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class InfoActivity extends AppCompatActivity {

    TextView description;
    TextView information;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable
                (new ColorDrawable(getResources().getColor(R.color.red)));

        description = findViewById(R.id.descriptionId);
        information = findViewById(R.id.additionalInfoId);

        Intent intent = getIntent();
        String descriptionInfo = intent.getStringExtra("descriptionTransfer");
        String authorInfo = intent.getStringExtra("authorTransfer");
        String titleInfo = intent.getStringExtra("titleTransfer");
        String locationInfo = intent.getStringExtra("locationTransfer");

        String descriptionstr = descriptionInfo;
        description.setText(descriptionstr);

        String info = "Author: " + authorInfo + "\n"
                    + "Title: " + titleInfo + "\n"
                    + "Location: " + locationInfo + "\n";
        information.setText(info);


    }
}