package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class NewsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> newsList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable
                (new ColorDrawable(getResources().getColor(R.color.red)));

        listView = findViewById(R.id.list_news);
        newsList = new ArrayList<>();

        try{
            SQLiteDatabase db = this.openOrCreateDatabase("newsdb", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS articles (id INT PRIMARY KEY AUTOINCREMENT, " +
                    "author VARCHAR(255), title VARCHAR(255), description TEXT)");

            Cursor c = db.rawQuery("SELECT * FROM articles", null);
            int authorIndex = c.getColumnIndex("author");
            int titleIndex = c.getColumnIndex("title");
            int descriptionIndex = c.getColumnIndex("description");
            c.moveToFirst();

            while (c != null){
                String article = c.getString(authorIndex)
                        + " " + c.getString(titleIndex) + " " + c.getString(descriptionIndex);

                newsList.add(article);
                c.moveToNext();
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newsList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener((adapterView, view, i, l) ->
                    Toast.makeText(getApplicationContext(), newsList.get(i), Toast.LENGTH_SHORT).show());

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void addArticle(View v){

        Intent i = new Intent(getApplicationContext(), AddingActivity.class);
        startActivity(i);

    }
}