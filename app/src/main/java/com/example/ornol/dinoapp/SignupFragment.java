package com.example.ornol.dinoapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.ornol.dinoapp.http.ApiCall;
import com.example.ornol.dinoapp.json.JsonJavaConverter;
import com.example.ornol.dinoapp.searchParams.SearchParams;
import com.example.ornol.dinoapp.users.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends DialogFragment {
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

    public EditText[] Edits= new EditText[10];
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
        Edits[0]=(EditText) myFragmentView.findViewById(R.id.restaurantName);
        Edits[1]=(EditText) myFragmentView.findViewById(R.id.email);
        Edits[2]=(EditText) myFragmentView.findViewById(R.id.Password);
        Edits[3]=(EditText) myFragmentView.findViewById(R.id.PasswordConfirmation);
        Edits[4]=(EditText) myFragmentView.findViewById(R.id.PhoneNumber);
        Edits[5]=(EditText) myFragmentView.findViewById(R.id.Website);
        Edits[6]=(EditText) myFragmentView.findViewById(R.id.Address);
        Edits[7]=(EditText) myFragmentView.findViewById(R.id.City);
        Edits[8]=(EditText) myFragmentView.findViewById(R.id.PostalCode);
        Edits[9]=(EditText) myFragmentView.findViewById(R.id.Description);
        for(int i = 0;i <Edits.length;i++) {
            Edits[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Edits[temp].setText("");
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
                        // Handle Signup Success.........  TODO
                        Log.d("Success: ", "WOHO!" );
                    }else{
                        // Handle Signup Failure......... TODO
                        Log.d("Failure: ", ":((((");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle errors in API call......... TODO
                }
                return null;
            }
        }.execute();
    }

    private void testCreateUser(){
        User user = new User("Test123456", "Test123456", "Test123456", 1233456, "Test123456", "Test123456", "Test123456", 333, "Test123456");
        createUser(user);
    }
}