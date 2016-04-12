package com.application.joodsmonument2016.app;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Jaarbeurs on 12-4-2016.
 */
public class MyCursorAdapter extends CursorAdapter{


    public MyCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.verhaal, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
TextView tvNaam = (TextView) view.findViewById(R.id.tvNaam);
        TextView tvVerhaal = (TextView) view.findViewById(R.id.tvVerhaal);
String naam = cursor.getString(cursor.getColumnIndexOrThrow("naam"));
       String verhaal = cursor.getString(cursor.getColumnIndexOrThrow("verhaal"));
        tvNaam.setText(naam);
        tvVerhaal.setText(verhaal);
    }
}
