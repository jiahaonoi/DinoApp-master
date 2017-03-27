package com.example.ornol.dinoapp;

import com.example.ornol.dinoapp.searchParams.SearchParams;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by Gunnar Thor on 27.3.2017.
 */

class OfferList {
    private static final OfferList ourInstance = new OfferList();

    static OfferList getInstance() {
        return ourInstance;
    }

    private List<Offer> offers = new ArrayList<>();
    private SearchParams searchParams = SearchParams.initSearchParams();
    private OkHttpClient client = new OkHttpClient();

    private OfferList() {

    }


    void setOffers(List<Offer> offerList){
        this.offers=offerList;
    }

    List<Offer> getOffers() {
        return this.offers;
    }


    SearchParams getSearchParams() {
        return this.searchParams;
    }
}
