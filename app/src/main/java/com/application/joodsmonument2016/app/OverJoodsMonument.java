package com.application.joodsmonument2016.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class OverJoodsMonument extends ActionBarActivity {

    Button b1, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  ActionBar actionBar = getActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.RED)); // set your desired color
        setTitle("Over");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.over_joods_monument_layout);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toSite();
            }
        });
        setTitle("Over");
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toSiteSpoorwegmuseum();
            }
        });

    }

    public void toSite(){
        Uri uri = Uri.parse("http://www.joodsmonumentutrecht.nl");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    public void toSiteSpoorwegmuseum(){
        Uri uri = Uri.parse("http://82.192.77.193/beladentreinen/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.lobbies:
                finish();
                startActivity(new Intent(getApplicationContext(), OverJoodsMonument.class));
                return true;
            case R.id.lobbycreation:
                finish();
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                return true;
            case R.id.muur:

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
