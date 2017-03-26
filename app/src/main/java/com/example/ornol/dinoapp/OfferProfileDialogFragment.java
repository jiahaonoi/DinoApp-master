package com.example.ornol.dinoapp;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.math.*;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OfferProfileDialogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OfferProfileDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OfferProfileDialogFragment extends android.support.v4.app.DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Offer offer;
    private View myFragmentView;

    public OfferProfileDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OfferProfileDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OfferProfileDialogFragment newInstance(String param1, String param2) {
        OfferProfileDialogFragment fragment = new OfferProfileDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //getDialog().getWindow().setLayout(600,800);
    }
    int width = 0;
    int height = 0;
    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }
    @Override
    public void onResume(){
        getDialog().getWindow().setLayout(width,height);
        super.onResume();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        populateOffer();
        myFragmentView =inflater.inflate(R.layout.offer_profile_view, container, false);

        ImageView image = (ImageView) myFragmentView.findViewById(R.id.image);
        TextView name = (TextView) myFragmentView.findViewById(R.id.name);
        TextView restauranName = (TextView) myFragmentView.findViewById(R.id.restaurantName);
        TextView price = (TextView) myFragmentView.findViewById(R.id.price);
        TextView type = (TextView) myFragmentView.findViewById(R.id.type);
        TextView description = (TextView) myFragmentView.findViewById(R.id.Description);

        image.setImageResource(R.drawable.humberger);
        name.setText(""+offer.getName());
        restauranName.setText(""+offer.getRestName());
        price.setText(""+offer.getPrice());
        type.setText(""+offer.getType());
        description.setText(""+offer.getDescription());

        return myFragmentView;
    }

    public void populateOffer(){
        offer = new Offer(123, "humberger", "Fast Food", 1200, new Date(), "testing description", new Date(),
                "humberger.jpg", 123,"Dino", "20,Mars,2017","20,Mars,2018", new int[]{0,1,1,1,0,0,0});
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
}
