package com.example.ornol.dinoapp.users;

import android.util.Log;

import java.net.URL;

/**
 * Created by gudbjartursigurbergsson on 29/03/2017.
 */

// Todo implement the methods in this class
public class Validation {
    public boolean restaurantNameValid(String restaurantName){
        if(restaurantName.length() == 0) return false;
        return true;
    }

    public boolean emailValid(String email){
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(EMAIL_REGEX);
    }

    public boolean passwordValid(String password){
        if(password.length() < 5) return false;
        return true;
    }

    public boolean passwordsMatch(String password, String password2){
        if(password.equals(password2)) return true;
        return false;
    }

    public boolean phonenumberValid(int phonenumber) {
        if(String.valueOf(phonenumber).length() != 7) return false;
        return true;
    }

    public boolean urlValid(String url){
        try {
            URL urlValid = new URL(url);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean addressValid(String address){
        if(address.length() == 0) return false;
        return true;
    }

    public boolean cityValid(String city){
        if(city.length() == 0) return false;
        return true;
    }

    public boolean postCodeValid(int postCode){
        if(String.valueOf(postCode).length() != 3) return false;
        return true;
    }

    public boolean descriptionValid(String description){
        return true;
    }
}
