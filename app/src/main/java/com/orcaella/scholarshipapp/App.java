package com.orcaella.scholarshipapp;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class App extends Application {

    private Gson gson;
    private static App instance;

    public App() {
        instance = this;
    }

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    public Gson getGson() {
        return gson;
    }
}
