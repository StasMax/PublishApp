package com.example.android.publishapp.presentation.mvp.ui;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.presentation.adapter.PublishDiffUtilCallback;
import com.example.android.publishapp.presentation.adapter.PublishPagedListAdapter;
import com.example.android.publishapp.presentation.app.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_publishes)
    RecyclerView recyclerView;
    @BindView(R.id.txt_empty_list)
    TextView textEmptyList;
    private PublishPagedListAdapter pagerAdapter;
    @Inject
    LiveData<PagedList<PublishModel>> pagedListLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PublishDiffUtilCallback publishDiffUtil = new PublishDiffUtilCallback();
        pagerAdapter = new PublishPagedListAdapter(publishDiffUtil.diffUtilCallback);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), OrientationHelper.VERTICAL, false));
        recyclerView.setAdapter(pagerAdapter);
        pagedListLiveData.observe(this, publishModels -> pagerAdapter.submitList(publishModels));
    }

    @OnClick(R.id.float_button)
    void onSaveClick() {
        startActivity(new Intent(MainActivity.this, PublishActivity.class));
    }
}
