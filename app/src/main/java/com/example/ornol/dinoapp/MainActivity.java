package com.example.ornol.dinoapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;


import com.example.ornol.dinoapp.http.ApiCall;
import com.example.ornol.dinoapp.json.JsonJavaConverter;
import com.example.ornol.dinoapp.searchParams.PriceRange;
import com.example.ornol.dinoapp.searchParams.SearchParams;
import com.example.ornol.dinoapp.searchParams.SortBy;
import com.example.ornol.dinoapp.searchParams.Type;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;


public class MainActivity extends FragmentActivity implements LoginFragment.OnFragmentInteractionListener,SignupFragment.OnFragmentInteractionListener{

    // OkHttpClient for API Calls
    private OkHttpClient client;

    private static LoginFragment editNameDialogFragment = new LoginFragment();
    private static SignupFragment SDialogFragment = new SignupFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the OkHttpClient
        client = new OkHttpClient();
    }
    public void onFragmentInteraction(Uri uri){

    }
    public void DisplayLogin(View view){
        showLoginDialog();
    }

    private void showLoginDialog() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        editNameDialogFragment.show(fm, "fragment_edit_name");

        // Test the method
//        loadOffers(createDummySearchParams());

    }
    private void showSignupDialog() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        SDialogFragment.show(fm, "fragment_edit_name");
    }
    public void DisplaySignUp(View view){
        showSignupDialog();
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

    private static SearchParams createDummySearchParams() {
        SearchParams searchParams = new SearchParams();

        searchParams.setSearchBar("");

        PriceRange priceRange = new PriceRange();
        priceRange.setLow(0);
        priceRange.setHigh(10000);
        searchParams.setPriceRange(priceRange);

        Type fastFood = new Type();
        fastFood.setActive("");
        fastFood.setName("Fast Food");

        Type fineDining = new Type();
        fineDining.setActive("");
        fineDining.setName("Fine Dining");

        Type bistro = new Type();
        bistro.setActive("");
        bistro.setName("Bistro");

        Type vegan = new Type();
        vegan.setActive("");
        vegan.setName("Vegan");

        Type types[] = new Type[4];
        types[0] = fastFood;
        types[1] = fineDining;
        types[2] = bistro;
        types[3] = vegan;

        searchParams.setTypes(types);

        SortBy price = new SortBy();
        price.setChecked("");
        price.setName("Price");

        SortBy name = new SortBy();
        name.setChecked("checked");
        name.setName("Name");

        SortBy restaurant = new SortBy();
        restaurant.setChecked("");
        restaurant.setName("Restaurant");

        SortBy type = new SortBy();
        type.setChecked("");
        type.setName("Type");

        SortBy sortBy[] = new SortBy[4];
        sortBy[0] = price;
        sortBy[1] = name;
        sortBy[2] = restaurant;
        sortBy[3] = type;

        searchParams.setSortBy(sortBy);

        SortBy ascending = new SortBy();
        ascending.setChecked("checked");
        ascending.setName("Ascending");

        SortBy descending = new SortBy();
        descending.setChecked("");
        descending.setName("Descending");

        SortBy ordering[] = new SortBy[2];
        ordering[0] = ascending;
        ordering[1] = descending;

        searchParams.setOrdering(ordering);



        return searchParams;
    }
}