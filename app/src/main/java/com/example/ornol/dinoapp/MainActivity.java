package com.example.ornol.dinoapp;

import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import 	android.view.MenuInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.util.ArrayList;


import com.example.ornol.dinoapp.http.ApiCall;
import com.example.ornol.dinoapp.json.JsonJavaConverter;
import com.example.ornol.dinoapp.searchParams.PriceRange;
import com.example.ornol.dinoapp.searchParams.SearchParams;
import com.example.ornol.dinoapp.searchParams.SortBy;
import com.example.ornol.dinoapp.searchParams.Type;
//import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

//import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener,SignupFragment.OnFragmentInteractionListener{

    // OkHttpClient for API Calls
   // private OkHttpClient client;

    private static LoginFragment editNameDialogFragment = new LoginFragment();
    private static SignupFragment SDialogFragment = new SignupFragment();
    private List<Object> myList = new ArrayList<Object>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateMyList();
        populateListView();
        ClickCallback();
        // Initialize the OkHttpClient
        //client = new OkHttpClient();
    }
    public void onFragmentInteraction(Uri uri){

    }

    private void showLoginDialog() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

    private void showSignupDialog() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        SDialogFragment.show(fm, "fragment_edit_name");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.login:
                showLoginDialog();
                break;
            case R.id.signup:
                showSignupDialog();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //-----------------------------------------ListView-----------------------------
    //set data on myList.
    public void populateMyList(){
        myList.add(new Object[]{"Humberger Menu1",1400,"With vegetable, fries and coke"});
        myList.add(new Object[]{"Humberger Menu2",1600,"With bacon, vegetable, fries and coke"});
        myList.add(new Object[]{"Humberger Menu3",1800,"With bacon, egg, vegetable, fries and coke"});
        myList.add(new Object[]{"Humberger Menu4",2000,"With extra humberger, bacon, egg, vegetable, fries and coke"});
    }

    public void populateListView(){
        ArrayAdapter<Object> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    //Adapter for ListView
    private class MyListAdapter extends ArrayAdapter<Object>{
        public MyListAdapter(){
            super(MainActivity.this,R.layout.activity_main, myList);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }
            Object[] currentItem = (Object[]) myList.get(position);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
            imageView.setImageResource(R.drawable.humberger);

            TextView name = (TextView) itemView.findViewById(R.id.name);
            name.setText("Name:" + currentItem[0]);

            TextView price = (TextView) itemView.findViewById(R.id.price);
            price.setText("Price:" + (int) currentItem[1]);

            TextView description = (TextView) itemView.findViewById(R.id.description);
            description.setText("Description:" + currentItem[2]);
            return itemView;
        }
    }
    //set onItemClick for ListView.
    private void ClickCallback(){
        final ListView list = (ListView)findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View viewClicked, int position, long id){
                int itemPosition     = position;

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
    // Loads Offers From Web Server based on jsonString representation of searchParams.
    private void loadOffers(SearchParams searchParams) {
        JsonJavaConverter jsonJavaConverter = new JsonJavaConverter();
        final String jsonString = jsonJavaConverter.JavaObjectToJsonString(searchParams);
        final String url = "http://dino-web.herokuapp.com/api-offerlist";
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params){
                try {
                    String response = ApiCall.POST(client, url, jsonString);
                    Log.d("Response", response);
                    JsonJavaConverter jsonJavaConverter = new JsonJavaConverter();

                    // Try to turn our response to List of offers
                    try {
                        // Response on right format, carry on.
                        List<Offer> offerList = jsonJavaConverter.jsonStringToListOfJavaObjects(response);

                        // Success handling .... TODO
                        Log.d("Success: ", "WOHO!");

                    }catch (Exception e) {
                        // Error in response
                        // Error handling ..... TODO
                        Log.d("Failure: ", ":((((");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle errors..........
                }
                return null;
            }
        }.execute();
    }
}