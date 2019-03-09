package com.hacker.modernapparch;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;

public class AppController extends Application {


    private Context mycontext;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        mycontext = getApplicationContext();
        // Or, you can define it manually.
    }
}
