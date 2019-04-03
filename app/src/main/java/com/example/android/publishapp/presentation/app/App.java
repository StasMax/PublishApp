package com.example.android.publishapp.presentation.app;

import android.app.Application;

import com.example.android.publishapp.di.component.AppComponent;
import com.example.android.publishapp.di.component.DaggerAppComponent;
import com.example.android.publishapp.di.module.AppModule;
import com.example.android.publishapp.di.module.PublishModule;
import com.example.android.publishapp.di.module.RetrofitModule;

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .publishModule(new PublishModule())
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule())
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
