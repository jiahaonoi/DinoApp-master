package com.example.ornol.dinoapp;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddOrDeleteOfferFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddOrDeleteOfferFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddOrDeleteOfferFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private boolean addOrDelete = true;

    private OnFragmentInteractionListener mListener;

    public AddOrDeleteOfferFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddOrDeleteOfferFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddOrDeleteOfferFragment newInstance(String param1, String param2) {
        AddOrDeleteOfferFragment fragment = new AddOrDeleteOfferFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        if(addOrDelete==true){
            view = inflater.inflate(R.layout.add_offer, container, false);
            height = height *8/10;
            Button addButton = (Button) view.findViewById(R.id.addOffer);
            addButton.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    closeFragment();
                }
            });
        } else {
            view = inflater.inflate(R.layout.delete_offer, container, false);
            height = height *4/10;
            Button deleteButton = (Button) view.findViewById(R.id.deleteOffer);
            deleteButton.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    closeFragment();
                }
            });
        }
        Button deleteButton = (Button) view.findViewById(R.id.Cancel);
        deleteButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                closeFragment();
            }
        });
        return view;
    }
    void closeFragment(){
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void setAddOrDelete(boolean addOrDelete){
        this.addOrDelete = addOrDelete;
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
