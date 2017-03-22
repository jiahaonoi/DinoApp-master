package com.example.ornol.dinoapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;


import com.example.ornol.dinoapp.searchParams.PriceRange;
import com.example.ornol.dinoapp.searchParams.SearchParams;
import com.example.ornol.dinoapp.searchParams.SortBy;
import com.example.ornol.dinoapp.searchParams.Type;
import com.google.gson.Gson;


public class MainActivity extends FragmentActivity implements LoginFragment.OnFragmentInteractionListener,SignupFragment.OnFragmentInteractionListener{

    private static LoginFragment editNameDialogFragment = new LoginFragment();
    private static SignupFragment SDialogFragment = new SignupFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}