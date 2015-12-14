package com.example.tyler.finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BeveragesMain extends AppCompatActivity {

    private ListView beveragesListView;
    private BeverageList beverageList;
    private ArrayAdapter beverageAdapter;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        String url = "http://tyler.finalproject.dennisiscool.tech/beverages/json";
        beveragesListView = (ListView)findViewById(R.id.beveragesListView);
        beverageList = new BeverageList();
        final Context context = this;
        final JsonArrayRequest jsonRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            int beverage_id = 0;
                            String beverage_name = "";
                            String jsonResponse = "";
                            String beverage_type = "";
                            double average_rating = 0.0;
                            for(int i = 0; i < response.length(); i++) {
                                JSONObject object = (JSONObject)response.get(i);
                                beverage_id = object.getInt("beverage_id");
                                beverage_name = object.getString("beverage_name");
                                beverage_type = object.getString("beverage_type");
                                jsonResponse += "beverage_id: " + beverage_id + "\n";
                                jsonResponse += "beverage_name: " + beverage_name + "\n";
                                jsonResponse += "beverage_type: " + beverage_type + "\n";
                                if(!object.isNull("average_rating")) {
                                    average_rating = object.getDouble("average_rating");
                                    jsonResponse += "average_rating: " + average_rating + "\n\n";
                                }
                                else {
                                    jsonResponse += "average_rating: null\n\n";
                                }
                                Beverage beverage = new Beverage(beverage_id,beverage_name,beverage_type,average_rating);
                                beverageList.add(beverage);
                                Log.i("beverage",jsonResponse);
                            }
                            ArrayAdapter<Beverage> beverageAdapter = new ArrayAdapter(
                                    context,
                                    R.layout.list_item,
                                    beverageList.getNames()
                            );
                            beveragesListView.setAdapter(beverageAdapter);
                            beveragesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                                    Intent intent = new Intent(context.getApplicationContext(), BeverageShow.class);
                                    int bev_id = position + 1;
                                    String name = beverageList.getNames()[position];
                                    String type = beverageList.getList().get(position).getType();
                                    double avgRating = beverageList.getList().get(position).getAverage_rating();
                                    intent.putExtra("position", bev_id);
                                    intent.putExtra("name", name);
                                    intent.putExtra("type", type);
                                    intent.putExtra("avgRating", avgRating);
                                    startActivity(intent);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(this).add(jsonRequest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BeveragesMain.this, "You've clicked on the FAB!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_add) {
            Intent intent = new Intent(BeveragesMain.this,BeverageCreateBeer.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
