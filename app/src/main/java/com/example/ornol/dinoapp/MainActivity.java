package com.example.ornol.dinoapp;

import android.app.FragmentTransaction;
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

import com.example.ornol.dinoapp.http.ApiCall;
import com.example.ornol.dinoapp.json.JsonJavaConverter;
import com.example.ornol.dinoapp.searchParams.SearchParams;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements ErrorHandle.OnFragmentInteractionListener,LoginFragment.OnFragmentInteractionListener,SignupFragment.OnFragmentInteractionListener,OfferProfileDialogFragment.OnFragmentInteractionListener,SearchDialogFragment.OnFragmentInteractionListener{

    // OkHttpClient for API Calls
    private OkHttpClient client;
    private static OfferProfileDialogFragment OfferDialogFragment = new OfferProfileDialogFragment();
    private static LoginFragment editNameDialogFragment = new LoginFragment();
    private static SignupFragment SDialogFragment = new SignupFragment();
    private static SearchDialogFragment searchDialog = new SearchDialogFragment();
    private static ErrorHandle EHDialogFragment = new ErrorHandle();
    private OfferList theOfferList = OfferList.getInstance();
    private MyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EHDialogFragment.set(this);
        ClickCallback();

        // Initialize the OkHttpClient
        client = new OkHttpClient();
        getList();
    }

    @Override
    protected void onResume(){
        super.onResume();
        theOfferList=OfferList.getInstance();
        getList();
    }

    protected void getList(){
        loadOffers(theOfferList.getSearchParams());
        setScreenSize();
    }

    public void refreshMain(){
        loadOffers(theOfferList.getSearchParams());
        EHDialogFragment.closefragment();
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

    private void showOfferProfileDialog(Offer offer){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Log.d("MyApp",height+","+width);
        // Get offer and send on
        OfferDialogFragment.setOffer(offer);
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

    public void showErrorDialog(){
        Log.d("MyApp",height+","+width);
        //Set screen size to DialogFragment
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        EHDialogFragment.setSize(width, height);
        EHDialogFragment.show(ft, "fragment_edit_name");
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
        mAdapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(mAdapter);
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
            int size = itemView.getLayoutParams().width;
            itemView.setLayoutParams(new ViewGroup.LayoutParams(width/9*10,height/9*2 ));

            Offer currentItem = theOfferList.getOffers().get(position);
            //ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
            //imageView.setImageResource(R.drawable.humberger);

            TextView name = (TextView) itemView.findViewById(R.id.name);
            name.setText(currentItem.getName());

            TextView price = (TextView) itemView.findViewById(R.id.price);
            price.setText(currentItem.getPrice()+" kr");

            TextView type = (TextView) itemView.findViewById(R.id.type);
            type.setText(currentItem.getType());


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
                //Toast.makeText(getApplicationContext(),
                //        "Position :"+itemPosition, Toast.LENGTH_LONG)
                //        .show();
                Offer currentOffer = theOfferList.getOffers().get(position);
                showOfferProfileDialog(currentOffer);
            }
        });
    }

    public void onSearchButtonClicked(View view) {
        getList();
    }

    // Loads Offers From Web Server based on jsonString representation of searchParams.
    private void loadOffers(SearchParams searchParams) {
        Log.d("W","Loadoffer running");
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
                                Log.d("W","Loadoffer running!!!!");
                                populateListView();
                            }
                        });
                    }catch (Exception e) {
                        // Error in response
                        // Error handling ..... TODO
                        e.printStackTrace();
                        showErrorDialog();
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