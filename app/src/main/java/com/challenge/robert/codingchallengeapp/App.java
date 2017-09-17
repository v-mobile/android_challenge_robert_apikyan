package com.challenge.robert.codingchallengeapp;

import android.app.Application;

/**
 * Created by Robert on 17.09.2017.
 */

public class App extends Application {
    private static App instance;

    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
