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

    public SearchParams(){

    }

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

    public String getCheckedSortBy(){
        String s = "something went wrong";
        for(int i=0;i<sortBy.length;i++){
            if(sortBy[i].getChecked().equals("checked")) {
                return sortBy[i].getName();
            }
        }
        return s;
    }

    public void setCheckedSortBy(int n) {
        for(int i=0;i<sortBy.length;i++){
            sortBy[i].setChecked("");
        }
        sortBy[n].setChecked("checked");
    }

    public SortBy[] getOrdering() {
        return ordering;
    }

    public void setOrdering(SortBy[] ordering) {
        this.ordering = ordering;
    }

    public String getCheckedOrdering(){
        String s = "something went wrong";
        for(int i=0;i<ordering.length;i++){
            if(ordering[i].getChecked().equals("checked")) {
                return ordering[i].getName();
            }
        }
        return s;
    }
    public static SearchParams initSearchParams() {
        SearchParams searchParams = new SearchParams();

        searchParams.setSearchBar("");

        PriceRange priceRange = new PriceRange();
        priceRange.setLow(0);
        priceRange.setHigh(10000);
        searchParams.setPriceRange(priceRange);

        Type fastFood = new Type();
        fastFood.setActive("");
        fastFood.setName("Fast Food");

        Type fineDining = new Type();
        fineDining.setActive("");
        fineDining.setName("Fine Dining");

        Type bistro = new Type();
        bistro.setActive("");
        bistro.setName("Bistro");

        Type vegan = new Type();
        vegan.setActive("");
        vegan.setName("Vegan");

        Type types[] = new Type[4];
        types[0] = fastFood;
        types[1] = fineDining;
        types[2] = bistro;
        types[3] = vegan;

        searchParams.setTypes(types);

        SortBy price = new SortBy();
        price.setChecked("");
        price.setName("Price");

        SortBy name = new SortBy();
        name.setChecked("checked");
        name.setName("Name");

        SortBy restaurant = new SortBy();
        restaurant.setChecked("");
        restaurant.setName("Restaurant");

        SortBy type = new SortBy();
        type.setChecked("");
        type.setName("Type");

        SortBy sortBy[] = new SortBy[4];
        sortBy[0] = price;
        sortBy[1] = name;
        sortBy[2] = restaurant;
        sortBy[3] = type;

        searchParams.setSortBy(sortBy);

        SortBy ascending = new SortBy();
        ascending.setChecked("checked");
        ascending.setName("Ascending");

        SortBy descending = new SortBy();
        descending.setChecked("");
        descending.setName("Descending");

        SortBy ordering[] = new SortBy[2];
        ordering[0] = ascending;
        ordering[1] = descending;

        searchParams.setOrdering(ordering);



        return searchParams;
    }
}