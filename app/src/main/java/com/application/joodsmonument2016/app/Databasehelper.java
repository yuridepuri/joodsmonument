package com.application.joodsmonument2016.app;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Jaarbeurs on 12-4-2016.
 */
public class Databasehelper extends SQLiteOpenHelper {

    private static Databasehelper theInstance = null;
 private Context fContext;
    private SQLiteDatabase database;

static final String TAG = "Yuri";


   public Databasehelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
       super(context, name, factory, version);
    }

    public static Databasehelper getInstance(Context ctx){
        if (theInstance == null)
            theInstance =
                    new Databasehelper(ctx, "joodsmonumentverhalen",
                            null, Constants.DATABASE_VERSION);
        return theInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE joodsmonumentverhalen ("
                + "_id INTEGER PRIMARY KEY,"
                + "naam TEXT,"
                + "verhaal_row_item TEXT"
                + ");");

        //Add default records to animals
        ContentValues _Values = new ContentValues();
        //Get xml resource file
        Resources res = fContext.getResources();

        //Open xml file
        XmlResourceParser _xml = res.getXml(R.xml.databasevalues);
        try
        {
            //Check for end of document
            int eventType = _xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //Search for record tags
                if ((eventType == XmlPullParser.START_TAG) &&(_xml.getName().equals("record"))){
                    //Record tag found, now get values and insert record
                    String _Naam = _xml.getAttributeValue(null, "naam");
                    String _Verhaal = _xml.getAttributeValue(null, "verhaal_row_item");
                    _Values.put("naam", _Naam);
                    _Values.put("verhaal_row_item", _Verhaal);
                    db.insert("joodsmonumentverhalen", null, _Values);
                }
                eventType = _xml.next();
            }
        }
        //Catch errors
        catch (XmlPullParserException e)
        {
            Log.e(TAG, e.getMessage(), e);
        }
        catch (IOException e)
        {
            Log.e(TAG, e.getMessage(), e);

        }
        finally
        {
            //Close the xml file
            _xml.close();
        }
    }

    /* Update database to latest version */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Crude update, make sure to implement a correct one when needed.

        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS animals");
        onCreate(db);
    }

    public Cursor getAllData () {

        String buildSQL = "SELECT * FROM " + "joodsmonumentverhalen";

        Log.d(TAG, "getAllData SQL: " + buildSQL);

        return database.rawQuery(buildSQL, null);
    }


//    public Cursor getVerhalen(){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.query("verhalen", null, null, null, null, null, "naam");
//        return c;
//    }
}

