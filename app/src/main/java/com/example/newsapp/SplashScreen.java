package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreen.this, UserActivity.class));
            finish();
        }, 2500);
    }
}
