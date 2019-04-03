package com.example.android.publishapp.di.component;

import com.example.android.publishapp.di.module.AppModule;
import com.example.android.publishapp.di.module.PublishModule;
import com.example.android.publishapp.di.module.RetrofitModule;
import com.example.android.publishapp.presentation.mvp.ui.MainActivity;
import com.example.android.publishapp.presentation.mvp.ui.PublishActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = {}, modules = {PublishModule.class, RetrofitModule.class, AppModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(PublishActivity publishActivity);

}