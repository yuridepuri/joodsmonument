package com.application.joodsmonument2016.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

/**
 * Created by Jaarbeurs on 29-4-2016.
 */
public class MainActivity extends ActionBarActivity {
private SliderLayout sliderShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        sliderShow = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Afbeelding 1",R.drawable.joodsmonument1);
        file_maps.put("Afbeelding 2",R.drawable.joodsmonument2);
        file_maps.put("Afbeelding 3",R.drawable.joodsmonument3);

        TextSliderView textSliderView= new TextSliderView(this);
TextSliderView textSliderView1 = new TextSliderView(this);
        TextSliderView textSliderView2 = new TextSliderView(this);

        textSliderView
//                .description("Afbeelding1")
                .image(R.drawable.joodsmonument1);

        textSliderView1
//                .description("Afbeelding2")
                .image(R.drawable.joodsmonument2);

        textSliderView2
//                .description("Afbeelding3")
                .image(R.drawable.joodsmonument3);


        sliderShow.addSlider(textSliderView);
sliderShow.addSlider(textSliderView1);
        sliderShow.addSlider(textSliderView2);
sliderShow.startAutoCycle();
    }

    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
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
            case R.id.namen:
                finish();
                startActivity(new Intent(getApplicationContext(), ShowNamesActivity.class));
                return true;
            case R.id.over:
                finish();
                startActivity(new Intent(getApplicationContext(), OverJoodsMonument.class));
                return true;
            case R.id.muur:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return true;
            case R.id.locatie:
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }




}
