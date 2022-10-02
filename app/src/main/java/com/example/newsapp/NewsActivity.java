package com.example.newsapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Objects;

public class NewsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> newsList;
    ArrayAdapter<String> adapter;

    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable
                (new ColorDrawable(getResources().getColor(R.color.red)));

        listView = findViewById(R.id.list_news);
        newsList = new ArrayList<>();

        try{

            db = this.openOrCreateDatabase("newsdb", MODE_PRIVATE, null);

            db.execSQL("CREATE TABLE IF NOT EXISTS articles (id int PRIMARY KEY AUTOINCREMENT, author VARCHAR(255), title VARCHAR(255), description TEXT, location VARCHAR(255))");

            Cursor c = db.rawQuery("SELECT description FROM articles", null);
            int descriptionIndex = c.getColumnIndex("description");
            c.moveToFirst();

            while (c != null){
                String article = c.getString(descriptionIndex) + "\n";

                newsList.add(article);
                c.moveToNext();

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
            startActivity(intent);
        });

    }

    public void addArticle(View v){

        Intent i = new Intent(getApplicationContext(), AddingActivity.class);
        startActivity(i);

    }




}