package com.example.android.publishapp.presentation.mvp.ui.fragment;

import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;

public class BaseFragmentActivity extends MvpAppCompatFragment {

    public void baseShowMessage(int resource) {
        Toast.makeText(getContext(), resource, Toast.LENGTH_SHORT).show();
    }
}
