package com.example.vikas.splesho;

import android.app.Application;

import com.firebase.client.Firebase;

public class kuch extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
