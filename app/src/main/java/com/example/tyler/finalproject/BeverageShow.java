package com.example.tyler.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BeverageShow extends AppCompatActivity {
    private TextView beverageNameTV;
    private TextView typeTV;
    private TextView avgRatingTV;
    private CommentList commentList;
    private ListView commentListView;
    private int beverage_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverage_show);
        beverageNameTV = (TextView)findViewById(R.id.beverageNameTV);
        typeTV = (TextView)findViewById(R.id.typeTV);
        avgRatingTV = (TextView)findViewById(R.id.avgRatingTV);
        commentList = new CommentList();
        commentListView = (ListView)findViewById(R.id.commentsListView);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("position") + 1;
        String url = "http://tyler.finalproject.dennisiscool.tech/beverages/jsonID/" + id;
        String name = b.getString("name");
        String type = b.getString("type");
        double avgRating = b.getDouble("avgRating");
        beverageNameTV.setText(name);
        typeTV.setText(type);
        avgRatingTV.setText(avgRating + "");
        final JsonArrayRequest jsonRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            int comment_id = 0;
                            beverage_id = 0;
                            String beverage_name = "";
                            String user_fname = "";
                            String jsonResponse = "";
                            String comment_descrip = "";
                            double rating = 0.0;
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = (JSONObject) response.get(i);
                                comment_id = object.getInt("comment_id");
                                beverage_id = object.getInt("beverage_id");
                                beverage_name = object.getString("beverage_name");
                                user_fname = object.getString("user_fname");
                                comment_descrip = object.getString("comment_descrip");
                                rating = object.getDouble("rating");
                                Comment comment = new Comment(comment_id,beverage_id,beverage_name,user_fname,comment_descrip,rating);
                                commentList.add(comment);
                            }
                            ArrayAdapter<Comment> commentAdapter = new ArrayAdapter(
                                    BeverageShow.this,
                                    R.layout.list_item,
                                    commentList.getText()
                            );
                            commentListView.setAdapter(commentAdapter);
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addpages, menu);
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
            Intent intent = new Intent(BeverageShow.this,BeveragesMain.class);
            startActivity(intent);
        }
        else if(id == R.id.action_rating) {
            Intent intent = new Intent(BeverageShow.this,BeverageAdd.class);
            intent.putExtra("beverage_id",beverage_id);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
