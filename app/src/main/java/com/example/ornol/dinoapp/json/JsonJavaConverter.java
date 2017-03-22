package com.example.ornol.dinoapp.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by gudbjartursigurbergsson on 22/03/2017.
 */

public class JsonJavaConverter {

    public <T> T jsonStringToJavaObject(String jsonString, Class<T> objectParameterClass) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, objectParameterClass);

    }

    public <T> List<T> jsonStringToListOfJavaObjects(String jsonString){
        Gson gson = new Gson();
        List<T> returnList = gson.fromJson(jsonString, new TypeToken<List<T>>(){}.getType());
        return returnList;
    }

    public <T> String JavaObjectToJsonString(T javaObject){
        Gson gson = new Gson();
        String json = gson.toJson(javaObject);
        return json;
    }
}

