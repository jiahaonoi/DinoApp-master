package com.example.ornol.dinoapp.users;

/**
 * Created by gudbjartursigurbergsson on 24/03/2017.
 */

public class User {
    private String restaurantName;
    private String email;
    private String password;
    private int phonenumber;
    private String website;
    private String address;
    private String city;
    private int postalcode;
    private String description;

    public User(String restaurantName, String email, String password, int phonenumber, String website, String address, String city, int postalcode, String description){
        this.restaurantName = restaurantName;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.website = website;
        this.address = address;
        this.city = city;
        this.postalcode = postalcode;
        this.description = description;
    }


    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
