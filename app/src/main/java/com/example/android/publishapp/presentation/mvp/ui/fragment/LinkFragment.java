package com.example.android.publishapp.presentation.mvp.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.publishapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinkFragment extends Fragment {


    public LinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_link, container, false);
    }

}
