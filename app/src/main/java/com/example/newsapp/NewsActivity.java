package com.example.newsapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Objects;

public class NewsActivity extends AppCompatActivity {

    ListView listView;
    Intent intent;

    int temp;

    ArrayList<String> newsList;
    ArrayList<String> authorsList;
    ArrayList<String> titlesList;
    ArrayList<String> locationsList;

    ArrayAdapter<String> adapter;

    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable
                (new ColorDrawable(getResources().getColor(R.color.red)));

        listView = findViewById(R.id.list_news);
        newsList = new ArrayList<>();
        authorsList = new ArrayList<>();
        titlesList = new ArrayList<>();
        locationsList = new ArrayList<>();

        try{

            db = this.openOrCreateDatabase("newsdb", MODE_PRIVATE, null);

            db.execSQL("CREATE TABLE IF NOT EXISTS articles (author VARCHAR(255), title VARCHAR(255), description TEXT, location VARCHAR(255))");

            Cursor c = db.rawQuery("SELECT * FROM articles", null);

            int authorIndex = c.getColumnIndex("author");
            int titleIndex = c.getColumnIndex("title");
            int descriptionIndex = c.getColumnIndex("description");
            int locationIndex = c.getColumnIndex("location");
            c.moveToFirst();

            if(c.getCount() == 0){
                Toast.makeText(getApplicationContext(), "No recent news, click on the + sign to add", Toast.LENGTH_SHORT).show();
            }else{
                while (c != null){
                    String description = c.getString(descriptionIndex) + "\n";
                    String author = c.getString(authorIndex);
                    String title = c.getString(titleIndex);
                    String location = c.getString(locationIndex);
                    newsList.add(description);
                    authorsList.add(author);
                    titlesList.add(title);
                    locationsList.add(location);
                    c.moveToNext();

                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            temp = i;
            intent = new Intent(getApplicationContext(), InfoActivity.class);

            String descriptionRow = newsList.get(temp);
            String authorsRow = authorsList.get(temp);
            String titlesRow = titlesList.get(temp);
            String locationsRow = locationsList.get(temp);

            intent.putExtra("descriptionTransfer", descriptionRow);
            intent.putExtra("authorTransfer", authorsRow);
            intent.putExtra("titleTransfer", titlesRow);
            intent.putExtra("locationTransfer", locationsRow);
            startActivity(intent);
        });
    }

    public void addArticle(View v){

        Intent i = new Intent(getApplicationContext(), AddingActivity.class);
        startActivity(i);

    }
}