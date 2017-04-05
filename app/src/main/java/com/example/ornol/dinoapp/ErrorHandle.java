package com.example.ornol.dinoapp;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ErrorHandle.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ErrorHandle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ErrorHandle extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ErrorHandle(){

    }

    @Override
    public void onResume(){
        getDialog().getWindow().setLayout(width,height/10*3);
        super.onResume();
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ErrorHandle.
     */
    // TODO: Rename and change types and number of parameters
    public static ErrorHandle newInstance(String param1, String param2) {
        ErrorHandle fragment = new ErrorHandle();
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
    private View myFragmentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragmentView = inflater.inflate(R.layout.fragment_error_handle, container, false);

        Button RefreshButton = (Button) myFragmentView.findViewById(R.id.RefreshButton);

        RefreshButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                refresh();
            }
        });

        Button CloseButton = (Button) myFragmentView.findViewById(R.id.CloseButton);

        CloseButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                closefragment();
            }
        });

        return  myFragmentView;
    }

    public void refresh(){
        mListener.refreshMain();
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

    public void closefragment() {
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    private int width=0;
    private int height=0;
    public void setSize(int width,int height){
        this.width = width;
        this.height = height;
    }

    public void set(OnFragmentInteractionListener listener){
        this.mListener = listener;
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
        void refreshMain();
    }
}
