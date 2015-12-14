package com.example.tyler.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class BeverageCreateWine extends AppCompatActivity {
    private Spinner typeSpinner;
    private EditText nameET;
    private EditText descriptionET;
    private EditText commentET;
    private RatingBar ratingBar;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverage_create_wine);
        typeSpinner = (Spinner)findViewById(R.id.typeSpinner);
        nameET = (EditText)findViewById(R.id.nameET);
        descriptionET = (EditText)findViewById(R.id.descriptionET);
        commentET = (EditText)findViewById(R.id.commentET);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        addButton = (Button)findViewById(R.id.addButton);
        String[] items = new String[]{"Beer", "Wine", "Mixed Drink"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setSelection(1);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(BeverageCreateWine.this, BeverageCreateBeer.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(BeverageCreateWine.this, BeverageCreateMD.class);
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
                String name = nameET.getText().toString();
                String description = descriptionET.getText().toString();
                String comment = commentET.getText().toString();
                double rating = ratingBar.getRating();
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
            Intent intent = new Intent(BeverageCreateWine.this,BeveragesMain.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
