package com.application.joodsmonument2016.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class ShowNamesActivity extends AppCompatActivity {
    //ArrayList<String> listdata;
    ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
    private Handler handler;
    EditText inputSearch;
    SimpleAdapter adapter;
    String afbeelding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names_view);
        new JsonParse().execute();

        inputSearch = (EditText)findViewById(R.id.inputSearch);
        inputSearch.getBackground().mutate().setColorFilter(getResources().getColor(R.color.material_deep_teal_500), PorterDuff.Mode.SRC_ATOP);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ShowNamesActivity.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



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
                    String twoObjectsItem = e.getString("field_achternaam");
                    String volnaam = oneObjectsItem+" "+twoObjectsItem;
                   Object item = e.get("field_afbeelding");
                    if(item instanceof JSONArray){
                    }else{
                        JSONObject afb = e.getJSONObject("field_afbeelding").getJSONObject("file");
                         afbeelding = afb.getString("uri");
                        Log.i("result5", String.valueOf(afb.length()));
                        Log.i("result6", afbeelding);
                    }

//                    Log.i("result7", afbeelding);
                    map.put("id", String.valueOf(i));
                   // map.put("field_naam", "" + e.getString("field_naam"));
                    map.put("field_naam", "" + volnaam);
                    map.put("achternaam", "" + e.getString("field_achternaam"));
                    map.put("afbeelding", "" + afbeelding);
                    map.put("verhaal", "" + e.getString("field_verhaal"));
                    map.put("geboren", "" + e.getString("field_geboren"));
                    map.put("field_gestorven", "† " + e.getString("field_gestorven"));
                    map.put("locatie", "" + e.getString("field_plaats_"));

                    mylist.add(map);
                    //ListAdapter
                            adapter = new SimpleAdapter(ShowNamesActivity.this, mylist, R.layout.names_list_items, new String[]
                            {"field_naam", "field_gestorven"},
                                    //"verhaal", "afbeelding", "gestorven"},
                                   // "verhaal", "afbeelding", "video"},
                            new int[]{R.id.naam, R.id.gestorven});
                    ListView lv = (ListView) findViewById(R.id.list);
                    lv.setTextFilterEnabled(true);
                    lv.setAdapter(adapter);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                               //Toast.makeText(ShowNamesActivity.this, "You Clicked at " + mylist.get(+position).get("field_naam"), Toast.LENGTH_SHORT).show();

                            String nm = mylist.get(+position).get("field_naam");
                            String an = mylist.get(+position).get("achternaam");
                            String vh = mylist.get(+position).get("verhaal");
                            String ab = mylist.get(+position).get("afbeelding");
                            String gb = mylist.get(+position).get("geboren");
                            String gs = mylist.get(+position).get("field_gestorven");
                            String lc = mylist.get(+position).get("locatie");
                           // String vd = mylist.get(+position).get("video");
                            if(vh.equals(null)||vh.equals("null")){
                                Toast.makeText(ShowNamesActivity.this, "No story on this one! " + mylist.get(+position).get("field_naam"), Toast.LENGTH_SHORT).show();
                            }
                            else{
                            Intent intent = new Intent(ShowNamesActivity.this, ReadStoryActivity.class);
                            intent.putExtra("nm", nm);
                            intent.putExtra("an", an);
                            intent.putExtra("vh", vh);
                             intent.putExtra("ab", ab);
                            intent.putExtra("gb", gb);
                            intent.putExtra("gs", gs);
                            intent.putExtra("lc", lc);
                            //intent.putExtra("vd", vd);
                            startActivity(intent);
                              // ShowNamesActivity.this.finish();
                            }
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
