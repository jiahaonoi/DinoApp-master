package com.example.ornol.dinoapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ornol.dinoapp.http.ApiCall;
import com.example.ornol.dinoapp.json.JsonJavaConverter;
import com.example.ornol.dinoapp.searchParams.SearchParams;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener,SignupFragment.OnFragmentInteractionListener,OfferProfileDialogFragment.OnFragmentInteractionListener,SearchDialogFragment.OnFragmentInteractionListener{

    // OkHttpClient for API Calls
    private OkHttpClient client;
    private static OfferProfileDialogFragment OfferDialogFragment = new OfferProfileDialogFragment();
    private static LoginFragment editNameDialogFragment = new LoginFragment();
    private static SignupFragment SDialogFragment = new SignupFragment();
    private static SearchDialogFragment searchDialog = new SearchDialogFragment();
    private OfferList theOfferList = OfferList.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClickCallback();

        // Initialize the OkHttpClient
        client = new OkHttpClient();
        loadOffers(theOfferList.getSearchParams());
        setScreenSize();
    }

    public void onFragmentInteraction(Uri uri){

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
    private void showLoginDialog() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Log.d("MyApp",height+","+width);
        //Set screen size to DialogFragment
        editNameDialogFragment.setSize(width, height);
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

    private void showSignupDialog() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Log.d("MyApp",height+","+width);
        //Set screen size to DialogFragment
        SDialogFragment.setSize(width, height);
        SDialogFragment.show(fm, "fragment_edit_name");
    }
    private void showOfferProfileDialog(){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Log.d("MyApp",height+","+width);
        //Set screen size to DialogFragment
        OfferDialogFragment.setSize(width, height);
        OfferDialogFragment.show(fm,"fragment_name");
    }

    public void showSearchDialog(View view){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Log.d("MyApp",height+","+width);
        //Set screen size to DialogFragment
        searchDialog.setSize(width, height);
        searchDialog.show(fm,"fragment_edit_name");
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

    public void populateListView(){
        MyListAdapter adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }
    //Adapter for ListView
    private class MyListAdapter extends ArrayAdapter<Offer> {
        MyListAdapter(){
            super(MainActivity.this,R.layout.activity_main, theOfferList.getOffers());
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }
            Offer currentItem = theOfferList.getOffers().get(position);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
            imageView.setImageResource(R.drawable.humberger);

            TextView name = (TextView) itemView.findViewById(R.id.name);
            name.setText("Name:" + currentItem.getName());

            TextView price = (TextView) itemView.findViewById(R.id.price);
            price.setText("Price:" + currentItem.getPrice());

            TextView description = (TextView) itemView.findViewById(R.id.description);
            description.setText("Description:" + currentItem.getDescription());
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
                        "Position :", Toast.LENGTH_LONG)
                        .show();
                showOfferProfileDialog();
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
                        List<Offer> offerList = jsonJavaConverter.jsonStringToListOfOffers(response);
                        theOfferList.setOffers(offerList);
                        //populateMyList(offerList);
                        // Can't change the view unless we are on the UI Thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                populateListView();
                            }
                        });
                    }catch (Exception e) {
                        // Error in response
                        // Error handling ..... TODO
                        e.printStackTrace();
//                        Log.d("Failure: ", e.toString());
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