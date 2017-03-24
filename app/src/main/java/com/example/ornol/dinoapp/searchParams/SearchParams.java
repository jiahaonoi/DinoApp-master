package com.example.ornol.dinoapp.searchParams;

/**
 * Created by gudbjartursigurbergsson on 22/03/2017.
 */

public class SearchParams {
    private String searchBar;
    private Type[] types;
    private PriceRange priceRange;
    // Some sortBy in "sortBy[]" needs to be checked for the API call to work.
    private SortBy[] sortBy;
    private SortBy[] ordering;

    public SearchParams(String searchBar, Type[] types, PriceRange priceRange, SortBy[] sortBy, SortBy[] ordering) {
        this.searchBar = searchBar;
        this.types = types;
        this.priceRange = priceRange;
        this.sortBy = sortBy;
        this.ordering = ordering;
    }

    public SearchParams(){};

    public String getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(String searchBar) {
        this.searchBar = searchBar;
    }

    public Type[] getTypes() {
        return types;
    }

    public void setTypes(Type[] types) {
        this.types = types;
    }

    public PriceRange getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
    }

    public SortBy[] getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy[] sortBy) {
        this.sortBy = sortBy;
    }

    public SortBy[] getOrdering() {
        return ordering;
    }

    public void setOrdering(SortBy[] ordering) {
        this.ordering = ordering;
    }
}