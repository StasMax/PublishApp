package com.example.android.publishapp.presentation.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.presentation.adapter.PaginationScrollListener;
import com.example.android.publishapp.presentation.adapter.PublishAdapterRv;
import com.example.android.publishapp.presentation.app.App;
import com.example.android.publishapp.presentation.mvp.presenter.MainPresenter;
import com.example.android.publishapp.presentation.mvp.view.MainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.android.publishapp.presentation.Constant.PAGE_START;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    @BindView(R.id.recycler_publishes)
    RecyclerView recyclerView;
    @BindView(R.id.txt_empty_list)
    TextView textEmptyList;
    @BindView(R.id.main_progress)
    ProgressBar progressBar;
    private PublishAdapterRv publishAdapterRv;
    private int currentPage = PAGE_START;
    private int TOTAL_PAGES = 5;
    private boolean isLoading = false, isLastPage = false;
    private List<PublishModel> listModels = new ArrayList<>();
    private List<PublishModel> itemsList = new ArrayList<>();
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

        publishAdapterRv = new PublishAdapterRv();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(publishAdapterRv);

        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                new Handler().postDelayed(() -> mainPresenter.initNextPage(currentPage), 2000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        mainPresenter.initFirstPage(currentPage);
    }

    public void loadFirstPage(List<PublishModel> publishModels) {

        progressBar.setVisibility(View.GONE);
        publishAdapterRv.addAll(publishModels);
        if (currentPage <= TOTAL_PAGES) publishAdapterRv.addLoadingFooter();
        else isLastPage = true;
    }


    public void loadNextPage(List<PublishModel> publishModels) {
        publishAdapterRv.removeLoadingFooter();
        isLoading = false;

        publishAdapterRv.addAll(publishModels);
        if (currentPage != TOTAL_PAGES) publishAdapterRv.addLoadingFooter();
        else isLastPage = true;
    }


    @Override
    public void setupEmptyList() {
        recyclerView.setVisibility(View.GONE);
        textEmptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void setupPublishList() {
        recyclerView.setVisibility(View.VISIBLE);
        textEmptyList.setVisibility(View.GONE);

        //  publishAdapterRv.addAll(publishList);
    }

    @OnClick(R.id.float_button)
    void onSaveClick() {
        startActivity(new Intent(MainActivity.this, PublishActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
