package com.example.android.publishapp.presentation.mvp.ui;

import android.content.Context;
import android.net.ConnectivityManager;

import com.arellomobile.mvp.MvpAppCompatActivity;

public class BaseActivity extends MvpAppCompatActivity {
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
