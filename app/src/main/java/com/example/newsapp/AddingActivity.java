package com.example.newsapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class AddingActivity extends AppCompatActivity {

    EditText ET_author;
    EditText ET_title;
    EditText ET_description;
    EditText ET_location;

    String author;
    String title;
    String description;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));


        ET_title = findViewById(R.id.titleInput);
        ET_author = findViewById(R.id.authorInput);
        ET_description = findViewById(R.id.descriptionInput);
        ET_location = findViewById(R.id.locationInput);

    }

    public void addItem(View v){

        title = ET_title.getText().toString();
        author = ET_author.getText().toString();
        description = ET_description.getText().toString();
        location = ET_location.getText().toString();

        if(!title.equals("") && !author.equals("") && !description.equals("") && !location.equals("")){
            NewsActivity.db.execSQL("INSERT INTO articles(author, title, description, location) VALUES ('"+author+"', '"+title+"', '"+description+"','"+location+"')");

            Toast.makeText(getApplicationContext(), "Article Added", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), NewsActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(), "Missing information", Toast.LENGTH_LONG).show();


        }



    }
}