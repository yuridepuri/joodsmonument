package com.application.joodsmonument2016.app;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
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
    private final Context fContext;
static final String TAG = "Yuri";
    Databasehelper(Context context) {
        super(context, "sampledb", null, 1);
        fContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE animals ("
                + "_id INTEGER PRIMARY KEY,"
                + "title TEXT,"
                + "color TEXT"
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
                    String _Verhaal = _xml.getAttributeValue(null, "verhaal");
                    _Values.put("naam", _Naam);
                    _Values.put("verhaal", _Verhaal);
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
}

