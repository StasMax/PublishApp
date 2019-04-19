package com.example.android.publishapp.presentation.app;

import android.app.Application;

import com.example.android.publishapp.di.component.AppComponent;
import com.example.android.publishapp.di.component.DaggerAppComponent;
import com.example.android.publishapp.di.module.PublishModule;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.android.publishapp.BuildConfig.BASE_URL;

public class App extends Application {

    private static AppComponent component;
    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .publishModule(new PublishModule())
                .build();

        FirebaseApp.initializeApp(this);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

}
