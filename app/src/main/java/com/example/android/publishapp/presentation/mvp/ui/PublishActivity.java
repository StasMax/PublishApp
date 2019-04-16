package com.example.android.publishapp.presentation.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android.publishapp.R;
import com.example.android.publishapp.presentation.adapter.FragmentsViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublishActivity extends FragmentActivity {
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radioButtonPost)
    RadioButton radioButtonPost;
    @BindView(R.id.radioButtonEvent)
    RadioButton radioButtonEvent;
    @BindView(R.id.radioButtonLink)
    RadioButton radioButtonLink;
    @BindView(R.id.pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        FragmentsViewPagerAdapter pagerAdapter = new FragmentsViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.beginFakeDrag();
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case -1:
                    break;
                case R.id.radioButtonPost:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.radioButtonEvent:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.radioButtonLink:
                    viewPager.setCurrentItem(2);
                    break;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}


