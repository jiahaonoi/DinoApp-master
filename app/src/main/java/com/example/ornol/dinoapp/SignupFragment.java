package com.example.ornol.dinoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ornol.dinoapp.http.ApiCall;
import com.example.ornol.dinoapp.json.JsonJavaConverter;
import com.example.ornol.dinoapp.users.User;
import com.example.ornol.dinoapp.users.Validation;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.OkHttpClient;

import static com.example.ornol.dinoapp.R.id.restaurantName;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends DialogFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public EditText[] edits = new EditText[10];
    public int temp = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    private View myFragmentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        myFragmentView =inflater.inflate(R.layout.fragment_signup, container, false);

        // Hook up the button
        Button signupButton = (Button)myFragmentView.findViewById(R.id.signup);
        signupButton.setOnClickListener(this);


        edits[0]=(EditText) myFragmentView.findViewById(restaurantName);
        edits[1]=(EditText) myFragmentView.findViewById(R.id.email);
        edits[2]=(EditText) myFragmentView.findViewById(R.id.Password);
        edits[3]=(EditText) myFragmentView.findViewById(R.id.PasswordConfirmation);
        edits[4]=(EditText) myFragmentView.findViewById(R.id.PhoneNumber);
        edits[5]=(EditText) myFragmentView.findViewById(R.id.Website);
        edits[6]=(EditText) myFragmentView.findViewById(R.id.Address);
        edits[7]=(EditText) myFragmentView.findViewById(R.id.City);
        edits[8]=(EditText) myFragmentView.findViewById(R.id.PostalCode);
        edits[9]=(EditText) myFragmentView.findViewById(R.id.Description);
        for(int i = 0; i < edits.length; i++) {
            edits[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edits[temp].setText("");
                }
            });
        }
        return myFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signup:
                if(validateInputFields()){
                    User user = userFromInputFields();
                    createUser(user);
                }
                break;
            default:
                break;
        }
    }

    private User userFromInputFields(){
        User user =
                new User(
                        edits[0].getText().toString(),
                        edits[1].getText().toString(),
                        edits[2].getText().toString(),
                        Integer.parseInt(edits[4].getText().toString()),
                        edits[5].getText().toString(),
                        edits[6].getText().toString(),
                        edits[7].getText().toString(),
                        Integer.parseInt(edits[8].getText().toString()),
                        edits[9].getText().toString());
        return user;
    }

    // Checks if input fields are valid.
    // Sets error message on the input fields if they are invalid.
    // Returns a boolean, true if valid, false otherwise.
    private boolean validateInputFields(){
        Validation validation = new Validation();
        boolean isValid = true;
        if (!validation.restaurantNameValid(edits[0].getText().toString())) {
            edits[0].setError("Restaurant name needs to include at least one character.");
            isValid = false;
        }

        if(!validation.emailValid(edits[1].getText().toString())){

            edits[1].setError("Email should be like example@example.com.");
            isValid = false;
        }

        if(!validation.passwordValid(edits[2].getText().toString())){
            edits[2].setError("Password should have length no less than 5.");
            isValid = false;
        }

        if(!validation.passwordsMatch(edits[2].getText().toString(), edits[3].getText().toString())){
            edits[3].setError("Passwords don't match.");
            isValid = false;
        }

        try {
            if (!validation.phonenumberValid(Integer.parseInt(edits[4].getText().toString()))) {
                edits[4].setError("Phonenumber should be 7 digits.");
                isValid = false;
            }
        } catch (Exception e){
            edits[4].setError("Phonenumber should be 7 digits.");
            isValid = false;
        }

        if(!validation.urlValid(edits[5].getText().toString())){
            edits[5].setError("Website URL should be like http://example.com.");
            isValid = false;
        }

        if(!validation.addressValid(edits[6].getText().toString())){
            edits[6].setError("Address should not be empty.");
            isValid = false;
        }

        if(!validation.cityValid(edits[7].getText().toString())){
            edits[7].setError("City should not be empty.");
            isValid = false;
        }

        try {

            if (!validation.postCodeValid(Integer.parseInt(edits[8].getText().toString()))) {
                edits[8].setError("Postal code should be of length 3.");
                isValid = false;
            }
        } catch (Exception e) {
            edits[8].setError("Postal code should be of length 3.");
            isValid = false;
        }

        if(!validation.descriptionValid(edits[9].getText().toString())){
            edits[9].setError("Description is invalid.");
            isValid = false;
        }

        return isValid;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    // Create user in our database through API POST request
    private void createUser(User user){
        final OkHttpClient client = new OkHttpClient();
        JsonJavaConverter jsonJavaConverter = new JsonJavaConverter();
        final String jsonString = jsonJavaConverter.JavaObjectToJsonString(user);
        final String url = "http://dino-web.herokuapp.com/users/api-signup";
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params){
                try {
                    String response = ApiCall.POST(client, url, jsonString);
                    Log.d("Response", response);

                    // response object looks like {success: true/false}
                    // Extract the value of success to Java Boolean, so we can work with it.
                    JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
                    Boolean result = jobj.get("success").getAsBoolean();

                    // Check if signup was successful:
                    if(result){
                        // Handle Signup Success.........  TODO: Success message, close fragment
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "Successfully created a new account.", Toast.LENGTH_LONG).show();

                                SignupFragment.super.dismiss();
                            }
                        });
                    }else{
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(),"Failed to create an account, try again.",Toast.LENGTH_LONG).show();

                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle errors in API call......... TODO
                }
                return null;
            }
        }.execute();
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }



    int width = 0;
    int height = 0;
    @Override
    public void onResume(){
        getDialog().getWindow().setLayout(width, height + 200);
        super.onResume();
    }



}