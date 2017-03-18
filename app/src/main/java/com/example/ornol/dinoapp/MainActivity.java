package com.example.ornol.dinoapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements BlankFragment.OnFragmentInteractionListener,Signup_Fragment.OnFragmentInteractionListener{

    private static BlankFragment editNameDialogFragment = new BlankFragment();
    private static Signup_Fragment SDialogFragment = new Signup_Fragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onFragmentInteraction(Uri uri){

    }
    public void DisplayLogin(View view){
        //Intent intent = new Intent (this, Activity_sigh_up.class);
        //startActivity(intent);
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
    public void DisplayMenus(View view){
        //Intent intent = new Intent (this, Activity_sigh_up.class);
        //startActivity(intent);
    }
}