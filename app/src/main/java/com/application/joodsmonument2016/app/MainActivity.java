package com.application.joodsmonument2016.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends Activity {
    Databasehelper dbh;
    Cursor theCursor;
    MyCursorAdapter adapter;
    private Context context;
    TextView tv;
    ListView lv;

    // TodoDatabaseHandler is a SQLiteOpenHelper class connecting to SQLite
   Databasehelper handler = new Databasehelper(this);
    // Get access to the underlying writeable database
    SQLiteDatabase db = handler.getWritableDatabase();
    // Query for items from the database and get a cursor back
    Cursor todoCursor = db.rawQuery("SELECT  * FROM todo_items", null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.uitgelicht);
        dbh = Databasehelper.getInstance(getApplicationContext());
        context = this;
        lv= (ListView)findViewById(R.id.list);

        theCursor = dbh.getAllData();
        adapter = new MyCursorAdapter(this, theCursor);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

/*        if (requestCode == ENTER_DATA_REQUEST_CODE && resultCode == RESULT_OK) {

            databaseHelper.insertData(data.getExtras().getString("tag_person_name"), data.getExtras().getString("tag_person_pin"));

            customAdapter.changeCursor(databaseHelper.getAllData());
        }*/
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
