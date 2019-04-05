package com.example.android.publishapp.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.publishapp.presentation.mvp.ui.fragment.EventFragment;
import com.example.android.publishapp.presentation.mvp.ui.fragment.LinkFragment;
import com.example.android.publishapp.presentation.mvp.ui.fragment.PostFragment;

public class FragmentsViewPagerAdapter extends FragmentPagerAdapter {
    static final int PAGE_COUNT = 3;

    public FragmentsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PostFragment();
            case 1:
                return new EventFragment();
            case 2:
                return new LinkFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}
