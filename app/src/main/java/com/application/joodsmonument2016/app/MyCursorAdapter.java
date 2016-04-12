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
    private LayoutInflater inflater;


    public MyCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void bindView(View arg0, Context arg1, Cursor arg2) {
        ViewHolder vh = (ViewHolder) arg0.getTag();
        if (vh == null){
            vh = new ViewHolder();
            vh.nameView = (TextView) arg0.findViewById(R.id.tvNaam);
            vh.verhaalView = (TextView) arg0.findViewById(R.id.tvVerhaal);
            arg0.setTag(vh);
        }
        String naam = arg2.getString(arg2.getColumnIndex("naam"));
        String verhaal = arg2.getString(arg2.getColumnIndex("verhaal"));

        vh.nameView.setText(naam);
        vh.verhaalView.setText(verhaal);
    }



    @Override
    public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
        View v = inflater.inflate(R.layout.verhaal_row_item, null);
        bindView(v, arg0, arg1);
        return v;
    }

    class ViewHolder {
        TextView nameView;
        TextView verhaalView;
    }





   /* @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.verhaal_row_item, parent, false);
    }*/




   /* @Override
    public void bindView(View view, Context context, Cursor cursor) {
TextView tvNaam = (TextView) view.findViewById(R.id.tvNaam);
        TextView tvVerhaal = (TextView) view.findViewById(R.id.tvVerhaal);
String naam = cursor.getString(cursor.getColumnIndexOrThrow("naam"));
       String verhaal = cursor.getString(cursor.getColumnIndexOrThrow("verhaal_row_item"));
        tvNaam.setText(naam);
        tvVerhaal.setText(verhaal);
    }*/
}
