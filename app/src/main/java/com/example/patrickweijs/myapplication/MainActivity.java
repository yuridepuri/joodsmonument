package com.example.patrickweijs.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;


public class MainActivity extends Activity {
Button fotoClick;
Button textClick;
Button fotoTextClick;
Button videoClick;
Button soundClick;
Button playBack;
Button kaasschaaf;
    PopupWindow pw;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
setTitle("Muur");
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        fotoClick = (Button) findViewById(R.id.I_Button);
        fotoClick.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)

            {

                likMijnLul();
//                //this is the code for popup window
//            LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View popupView1 = inflater.inflate(R.layout.popup1, null, false);
//            //Here x is the name of the xml which contains the popup components
//            pw = new PopupWindow(inflater.inflate(R.layout.popup1, null, false), 2200, 800, true);
//
//            //Here y is the id of the root component
//            pw.showAtLocation(findViewById(R.id.deze), Gravity.CENTER, 0, 0);
//
//
//
//            Button SLUIT = (Button) popupView1.findViewById(R.id.widget41);
//            Log.v("krijg de"," tiefus");
//            SLUIT.setOnClickListener(new View.OnClickListener() {
//
//                //@Override
//                public void onClick(View popupView1) {
//                    pw.dismiss();
//                    Log.v("krijg de"," kanker");
//                }
//            });

        }





        });

        textClick = (Button) findViewById(R.id.T_Button);
        textClick.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v)

            {
                //this is the code for popup window
                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //Here x is the name of the xml which contains the popup components
                   pw = new PopupWindow(inflater.inflate(R.layout.popup2,null, false),2200,1500,true);
                pw.setBackgroundDrawable(new BitmapDrawable());
                //Here y is the id of the root component
                pw.showAtLocation(findViewById(R.id.deze), Gravity.CENTER, 0,0);
            }
        });


        fotoTextClick = (Button) findViewById(R.id.IT_Button);
        fotoTextClick.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v)

            {
                //this is the code for popup window
                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //Here x is the name of the xml which contains the popup components
                PopupWindow  pw = new PopupWindow(inflater.inflate(R.layout.popup3,null, false),2200,1500,true);
                pw.setBackgroundDrawable(new BitmapDrawable());
                //Here y is the id of the root component
                pw.showAtLocation(findViewById(R.id.deze), Gravity.CENTER, 0,0);
            }
        });


        soundClick = (Button) findViewById(R.id.S_Button);
        soundClick.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v)

            {
                //this is the code for popup window
                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //Here x is the name of the xml which contains the popup components
                 pw = new PopupWindow(inflater.inflate(R.layout.popup4,null, false),2200,1500,true);
                pw.setBackgroundDrawable(new BitmapDrawable());
                //Here y is the id of the root component
                pw.showAtLocation(findViewById(R.id.deze), Gravity.CENTER, 0,0);



            }
        });



//        playBack = (Button) findViewById(R.id.Play);
//        playBack.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v)
//
//            {
//
//                // MediaPlayer mPlayer = MediaPlayer.create(PlayWorld.this, R.raw.Speech);
//                // start()
//
//
//            }
//        });




    }





   private void likMijnLul(){

      // this is the code for popup window
            LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupView1 = inflater.inflate(R.layout.popup1, null, false);
            //Here x is the name of the xml which contains the popup components
            pw = new PopupWindow(inflater.inflate(R.layout.popup1, null, false), 2200, 800, true);
            pw.setBackgroundDrawable(new BitmapDrawable());
            //Here y is the id of the root component
            pw.showAtLocation(findViewById(R.id.deze), Gravity.CENTER, 0, 0);





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
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
