package com.application.joodsmonument2016.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Yuri on 24-5-2016.
 */
public class ReadStoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readstory);
 Intent intent = getIntent();
        ImageView imageView = (ImageView) findViewById(R.id.imageView);


        String naam = intent.getStringExtra("nm");
        String achternaam = intent.getStringExtra("an");
        String verhaal = intent.getStringExtra("vh");
String geboren = intent.getStringExtra("gb");
        String gestorvenlogo = intent.getStringExtra("gs");
        String gestorven = gestorvenlogo.substring(2);
String locatie = intent.getStringExtra("lc");


        String afbeelding = intent.getStringExtra("ab");
String afbeeldingurl = afbeelding.substring(16);
//       Log.i("substring", afbeeldingurl);
        TextView tvnaam = (TextView) findViewById(R.id.naam);
        tvnaam.setText(naam);

        TextView tvgeb = (TextView) findViewById(R.id.geboortedatum);
        tvgeb.setText(geboren);

        TextView tvges = (TextView) findViewById(R.id.sterfdatum);
        tvges.setText(gestorven);

        TextView tvloc = (TextView) findViewById(R.id.sterflocatie);
        tvloc.setText(locatie);

        TextView tvverhaal = (TextView) findViewById(R.id.verhaal);
        tvverhaal.setText(verhaal);

        Picasso.with(this)
                .load("http://10.0.2.2/JoodsMonument/sites/default/files/"+afbeeldingurl)
                .into(imageView);
//        Log.i("intentstring", "http://10.0.2.2/JoodsMonument/sites/default/files/"+afbeeldingurl);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
