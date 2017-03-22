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
import com.google.gson.Gson;

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

    }
    private void showSignupDialog() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        SDialogFragment.show(fm, "fragment_edit_name");
    }
    public void DisplaySignUp(View view){
        showSignupDialog();
    }


    // Loads Offers From Web Server based on jsonString representation of searchParams.
    private void loadOffers(final String jsonString, final String url) {
        Log.d("Here We ", "Are");
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params){
                try {
                    String response = ApiCall.POST(client, url, jsonString);
                    Log.d("Response", response);
                    JsonJavaConverter jsonJavaConverter = new JsonJavaConverter();
                    List<Offer> offerList = jsonJavaConverter.jsonStringToListOfJavaObjects(response);
                    Log.d("Offer List ", String.valueOf(offerList.get(0)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}