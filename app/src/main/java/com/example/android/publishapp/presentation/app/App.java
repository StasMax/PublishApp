package com.example.android.publishapp.presentation.app;

import android.app.Application;


import com.example.android.publishapp.di.component.AppComponent;
import com.example.android.publishapp.di.component.DaggerAppComponent;
import com.example.android.publishapp.di.module.PublishModule;
import com.example.android.publishapp.di.module.RetrofitModule;
import com.google.firebase.FirebaseApp;

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .publishModule(new PublishModule())
                .retrofitModule(new RetrofitModule())
                .build();
        FirebaseApp.initializeApp(this);
    }

    public static AppComponent getComponent() {
        return component;
    }
}
