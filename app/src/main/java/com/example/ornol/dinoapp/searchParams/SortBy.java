package com.example.ornol.dinoapp.searchParams;

/**
 * Created by gudbjartursigurbergsson on 22/03/2017.
 */

public class SortBy {
    private String name = "";
    private String checked = "";

    public SortBy(){};

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}