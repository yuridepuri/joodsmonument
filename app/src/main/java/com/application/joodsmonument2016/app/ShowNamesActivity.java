package com.application.joodsmonument2016.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class ShowNamesActivity extends AppCompatActivity {
    //ArrayList<String> listdata;
    ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names_view);
        new JsonParse().execute();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_lobby, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.lobbies:
                finish();
                startActivity(new Intent(getApplicationContext(), ShowNamesActivity.class));
                return true;
//            case R.id.lobbycreation:
//                startActivity(new Intent(getApplicationContext(), MakeLobby.class));
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class JsonParse extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(ShowNamesActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONArray doInBackground(String... args) {

            JSONArray json =
                    JsonFromServer.getJSON("http://10.0.2.2/joodsmonument/node.json");

            return json;
        }


        @Override
        protected void onPostExecute(JSONArray json) {
            pDialog.dismiss();
            try {
                for (int i = 0; i < json.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    JSONObject e = json.getJSONObject(i);
                    String oneObjectsItem = e.getString("field_naam");
                    Log.i("result4", oneObjectsItem);
                    map.put("id", String.valueOf(i));
                    map.put("field_naam", "" + e.getString("field_naam"));
                    map.put("achternaam", "" + e.getString("field_achternaam"));
                    map.put("verhaal", "" + e.getString("field_verhaal"));
                    map.put("afbeelding", "" + e.getString("field_afbeelding"));
//                    map.put("video", "" + e.getString("field_video"));
//                    map.put("location", "" + e.getString("location"));
//                    map.put("numberparticipants", "" + e.getString("numberparticipants"));
//                    map.put("maxparticipants", "/" + e.getString("maxparticipants"));
//                    map.put("description", "" + e.getString("description"));
                    mylist.add(map);
                    ListAdapter adapter = new SimpleAdapter(ShowNamesActivity.this, mylist, R.layout.names_list_items, new String[]
                            {"field_naam", "verhaal", "afbeelding", "video"},
                                   // "verhaal", "afbeelding", "video"},
                            new int[]{R.id.naam});
                    ListView lv = (ListView) findViewById(R.id.list);
                    lv.setTextFilterEnabled(true);
                    lv.setAdapter(adapter);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            //   Toast.makeText(ShowNamesActivity.this, "You Clicked at " + mylist.get(+position).get("lobbyid"), Toast.LENGTH_SHORT).show();
                            String nm = mylist.get(+position).get("naam");
                            String vh = mylist.get(+position).get("verhaal");
                            String ab = mylist.get(+position).get("afbeelding");
                            String vd = mylist.get(+position).get("video");
//
                           // Intent intent = new Intent(ShowNamesActivity.this, ShowLobby.class);
//                            intent.putExtra("nm", nm);
//                            intent.putExtra("vh", vh);
//                            intent.putExtra("ab", ab);
//                            intent.putExtra("vd", vd);
//                            startActivity(intent);
                            //   ShowNamesActivity.this.finish();
                        }
                    });
                }
            } catch (
                    JSONException e
                    )

            {
                Log.e("Json exception", e.toString());
            }

        }
    }
}
