package com.example.android.publishapp.presentation.mvp.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;

public class BaseActivity extends MvpAppCompatActivity {
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public void baseShowMessage(int resource) {
        Toast.makeText(this, resource, Toast.LENGTH_SHORT).show();
    }
}

