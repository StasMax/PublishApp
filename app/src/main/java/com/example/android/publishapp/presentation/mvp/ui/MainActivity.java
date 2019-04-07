package com.example.android.publishapp.presentation.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.presentation.adapter.PublishAdapterRv;
import com.example.android.publishapp.presentation.app.App;
import com.example.android.publishapp.presentation.mvp.presenter.MainPresenter;
import com.example.android.publishapp.presentation.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    @BindView(R.id.recycler_publishes)
    RecyclerView recyclerView;
    @BindView(R.id.txt_empty_list)
    TextView textEmptyList;
    private PublishAdapterRv adapter;
    @Inject
    @InjectPresenter
    MainPresenter mainPresenter;

    @ProvidePresenter
    MainPresenter providePresenter() {
        return mainPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new PublishAdapterRv();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), OrientationHelper.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(int textResource) {
        Toast.makeText(this, textResource, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setupEmptyList() {
        recyclerView.setVisibility(View.GONE);
        textEmptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void setupPublishList(List<PublishModel> publishList) {
        recyclerView.setVisibility(View.VISIBLE);
        textEmptyList.setVisibility(View.GONE);
        adapter.setupPublishers(publishList);
    }

    @OnClick(R.id.float_button)
    void onSaveClick() {
        startActivity(new Intent(MainActivity.this, PublishActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.initPublishers();
    }
}
