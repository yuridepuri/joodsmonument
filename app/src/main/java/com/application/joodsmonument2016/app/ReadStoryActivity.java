package com.application.joodsmonument2016.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Yuri on 24-5-2016.
 */
public class ReadStoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readstory);
 Intent intent = getIntent();

        String naam = intent.getStringExtra("nm");
        String achternaam = intent.getStringExtra("an");


        Log.i("intentstring", naam+achternaam);



    }
}
