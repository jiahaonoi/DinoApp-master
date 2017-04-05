package com.example.ornol.dinoapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchDialogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static OfferList theOfferList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchDialogFragment newInstance(String param1, String param2) {
        SearchDialogFragment fragment = new SearchDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        theOfferList = OfferList.getInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_dialog, container, false);
        setSortByCheckedInView(view);
        getDialog().setTitle("Search");
        return view;
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
    // Displays correctly which 'Sort By' options are selected in SearchParams
    public void setSortByCheckedInView(View view)  {
        try {
            RadioGroup r = (RadioGroup) view.findViewById(R.id.sort_by_radio_group);
            RadioGroup s = (RadioGroup) view.findViewById(R.id.asc_desc);
            String checkedSortBy = theOfferList.getSearchParams().getCheckedSortBy();
            String checkedOrdering = theOfferList.getSearchParams().getCheckedOrdering();
            switch (checkedSortBy){
                case "Name":
                    r.check(R.id.sort_by_name);
                    break;
                case "Price":
                    r.check(R.id.sort_by_price);
                    break;
                case "Restaurant":
                    r.check(R.id.sort_by_restaurant);
                    break;
                case "Type":
                    r.check(R.id.sort_by_type);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid checked radio button "+theOfferList);
            }
            switch (checkedOrdering){
                case "Ascending":
                    s.check(R.id.ascending);
                    break;
                case "Descending":
                    s.check(R.id.descending);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid checked radio button "+theOfferList);
            }
        } catch (NullPointerException e) {
            Toast.makeText(getContext(),"FAILED",Toast.LENGTH_LONG).show();
            System.err.println("NullPointerException: "+e.getMessage());
        }
    }

    //TODO: Implement interactivity
    public void onRadioButtonClicked(View view){
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
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        }
    int width = 0;
    int height = 0;
    @Override
         public void onResume(){
        getDialog().getWindow().setLayout(width,height);
        super.onResume();
    }
}