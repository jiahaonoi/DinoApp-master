package com.example.ornol.dinoapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class RestaurantActivity extends AppCompatActivity implements AddOrDeleteOfferFragment.OnFragmentInteractionListener {
    ListView listView ;
    AddOrDeleteOfferFragment fragment = new AddOrDeleteOfferFragment();
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

        Button addButton = (Button) findViewById(R.id.addOffer);
        addButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                fragment.setAddOrDelete(true);
                ShowFragment();
            }
        });
        Button deleteButton = (Button) findViewById(R.id.deleteOffer);
        deleteButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                fragment.setAddOrDelete(false);
                ShowFragment();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        setScreenSize();
    }

    public void setScreenSize(){
        //Get screen size.
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.height = displayMetrics.heightPixels* 9/10;
        this.width = displayMetrics.widthPixels*9/10;
    }
    int height = 0;
    int width = 0;
    public void ShowFragment(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Log.d("MyApp",height+","+width);
        //Set screen size to DialogFragment
        fragment.setSize(width, height);
        fragment.show(ft,"fragment_edit_name");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.restaurant_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.home:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onFragmentInteraction(Uri uri){

    };
}
