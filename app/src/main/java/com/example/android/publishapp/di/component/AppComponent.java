package com.example.android.publishapp.di.component;

import com.example.android.publishapp.di.module.FirebaseModule;
import com.example.android.publishapp.di.module.PagerModule;
import com.example.android.publishapp.di.module.PublishModule;
import com.example.android.publishapp.di.module.RetrofitModule;
import com.example.android.publishapp.presentation.mvp.ui.MainActivity;
import com.example.android.publishapp.presentation.mvp.ui.PublishActivity;
import com.example.android.publishapp.presentation.mvp.ui.fragment.EventFragment;
import com.example.android.publishapp.presentation.mvp.ui.fragment.LinkFragment;
import com.example.android.publishapp.presentation.mvp.ui.fragment.PostFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = {}, modules = {PublishModule.class, PagerModule.class, RetrofitModule.class, FirebaseModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(PublishActivity publishActivity);

    void inject(PostFragment postFragment);

    void inject(EventFragment eventFragment);

    void inject(LinkFragment linkFragment);

}