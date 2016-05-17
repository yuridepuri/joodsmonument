package com.application.joodsmonument2016.app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Yuri on 27-6-2015.
 */
public class JsonFromServer {
    //public static JSONArray getJSON(String url) {
    public static JSONArray getJSON(String url){
        String result="";
        JSONArray jArray = null;
        JSONArray jsonArray2 = null;
        JSONObject jsonob = null;
        HttpURLConnection conn = null;
        try {
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-length", "0");
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            // conn.setConnectTimeout(timeout);
            // conn.setReadTimeout(timeout);
            conn.connect();
            int status = conn.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    result = sb.toString();

            }
        } catch (MalformedURLException ex) {

        } catch (IOException ex) {

        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception ex) {

                }
            }
        }
        try{
            //jArray = new JSONArray(result);
            jsonob = new JSONObject(result);
            jArray = jsonob.getJSONArray("list");
jsonArray2 = new JSONArray(result);
           // Gson gson = new Gson();
           // jArray=gson.toJson(result);




        }catch(JSONException e){
            Log.e("JSON exception", e.toString());
        }
        Log.i("ARRRRAAAAY", String.valueOf(jArray.length()));
        Log.i("result2", result);
        for (int i = 0; i < jArray.length(); i++) {
            try{
                JSONObject oneObject = jArray.getJSONObject(i);
                String oneObjectsItem = oneObject.getString("field_naam");
                Log.i("result3", oneObjectsItem);
            }catch (JSONException e){

            }

        }

        return jArray;
    }

}

