package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class userActivity extends AppCompatActivity {

    EditText name;
    Button newsBtn;
    SharedPreferences sp;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        getSupportActionBar().hide();

        name = findViewById(R.id.nameInput);
        newsBtn = findViewById(R.id.checkNewsButton);
        sp = getSharedPreferences("usernamePrefs", Context.MODE_PRIVATE);
    }

    public void checkNews(View v){

        username = name.getText().toString();

        if(!username.equals("")){
            Toast.makeText(getApplicationContext(), "Welcome, " +  username + "!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), NewsActivity.class);
            startActivity(i);

            newsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("name", username);
                    editor.commit();
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "Please enter your name!", Toast.LENGTH_LONG).show();
        }

    }
}