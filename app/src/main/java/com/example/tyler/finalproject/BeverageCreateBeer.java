package com.example.tyler.finalproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Rating;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class BeverageCreateBeer extends AppCompatActivity implements AsyncResponse {
    private Spinner typeSpinner;
    private EditText nameET;
    private EditText descriptionET;
    private EditText breweryET;
    private EditText commentET;
    private RatingBar ratingBar;
    private Button addButton;
    private String name;
    private String description;
    private String brewery;
    private String comment;
    private String rating;
    //private Task asyncTask;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverage_create);
        typeSpinner = (Spinner)findViewById(R.id.typeSpinner);
        nameET = (EditText)findViewById(R.id.nameET);
        descriptionET = (EditText)findViewById(R.id.descriptionET);
        breweryET = (EditText)findViewById(R.id.breweryET);
        commentET = (EditText)findViewById(R.id.commentET);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        addButton = (Button)findViewById(R.id.addButton);
        queue = Volley.newRequestQueue(getBaseContext());
        //asyncTask = new Task();
        //asyncTask.response = this;
        String[] items = new String[]{"Beer", "Wine", "Mixed Drink"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Intent intent = new Intent(BeverageCreateBeer.this, BeverageCreateWine.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(BeverageCreateBeer.this, BeverageCreateMD.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameET.getText().toString();
                description = descriptionET.getText().toString();
                brewery = breweryET.getText().toString();
                comment = commentET.getText().toString();
                rating = String.valueOf(ratingBar.getRating());
                String url = "http://tyler.finalproject.dennisiscool.tech/beverages/insert/beer";
                //asyncTask.execute("http://tyler.finalproject.dennisiscool.tech/beverages/insert/beer");
                final JsonArrayRequest jsonRequest = new JsonArrayRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // the response is already constructed as a JSONObject!
                                try {
                                    String status = "";
                                    String jsonResponse = "";
                                    for(int i = 0; i < response.length(); i++) {
                                        JSONObject object = (JSONObject)response.get(i);
                                        status = object.getString("status");
                                        jsonResponse += "status: " + status + "\n";
                                        Log.i("beverage",jsonResponse);
                                    }
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

                Volley.newRequestQueue(BeverageCreateBeer.this).add(jsonRequest);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void processFinish(String output) {
        Log.i("response1",output);
    }
    /*public class Task extends AsyncTask<String, Void, String> {
        public AsyncResponse response = null;
        @Override
        protected String doInBackground(String... string) {

            String responseStorage = ""; //storage of the response

            try {


                //Uses URL and HttpURLConnection for server connection.
                URL targetURL = new URL(string[0]);
                HttpURLConnection httpCon = (HttpURLConnection) targetURL.openConnection();
                httpCon.setReadTimeout(10000);
                httpCon.setConnectTimeout(15000);
                httpCon.setDoOutput(true);
                httpCon.setDoInput(true);
                httpCon.setUseCaches(false);
                httpCon.setChunkedStreamingMode(0);
                httpCon.setRequestMethod("GET");


                //sending request to the server.
                List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
                params.add(new Pair<>("name", name));
                params.add(new Pair<>("description", description));
                params.add(new Pair<>("brewery", brewery));
                params.add(new Pair<>("comment", comment));
                params.add(new Pair<>("rating", rating));
                OutputStream os = httpCon.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(params));
                writer.flush();
                //writer.close();
                //os.close();
                httpCon.connect();

                //getting the response from the server
                InputStream inputStream = httpCon.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String current = "";

                while ((current = br.readLine()) != null) {
                    responseStorage += current + "\n";
                }
                writer.close();
                os.close();
                inputStream.close();
                br.close();
            } catch (Exception aException) {
                responseStorage = aException.getMessage();
            }
            return responseStorage;
        }

        protected void onPostExecute(String result) {
            this.response.processFinish(result);
            Log.i("result",result);

        }

    }*/


    private String getQuery(List<Pair<String,String>> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Pair<String,String> pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.first, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.second, "UTF-8"));
        }

        return result.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_createpages, menu);
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
        else if(id == R.id.action_home) {
            Intent intent = new Intent(BeverageCreateBeer.this,BeveragesMain.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
