package com.example.ornol.dinoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RestaurantActivity extends AppCompatActivity {
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        listView = (ListView) findViewById(R.id.listOffer);
        String[] values = new String[] { "Chicken",
                "Fish and Chips",
                "Hamburger Offer",
                "Italian Buffet",
                "Kow Pad",
                "Pizza",
                "Shawarma",
                "Steak",
                "Vegan Burger"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
    }
}
