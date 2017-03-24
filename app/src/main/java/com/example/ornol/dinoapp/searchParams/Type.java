package com.example.ornol.dinoapp.searchParams;

/**
 * Created by gudbjartursigurbergsson on 22/03/2017.
 */

public class Type {
    private String name = "";
    // If this type is active this string has the value "active"
    private String active = "";

    public Type(){};

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}