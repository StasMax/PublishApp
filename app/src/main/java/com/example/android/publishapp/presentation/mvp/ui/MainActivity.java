package com.example.android.publishapp.presentation.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.presentation.adapter.PaginationScrollListener;
import com.example.android.publishapp.presentation.adapter.PublishAdapterRv;
import com.example.android.publishapp.presentation.app.App;
import com.example.android.publishapp.presentation.mvp.presenter.MainPresenter;
import com.example.android.publishapp.presentation.mvp.view.MainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.android.publishapp.presentation.Constant.LOAD_ITEM_SIZE;
import static com.example.android.publishapp.presentation.Constant.PAGE_START;

public class MainActivity extends BaseActivity implements MainView {
    @BindView(R.id.recycler_publishes)
    RecyclerView recyclerView;
    @BindView(R.id.txt_empty_list)
    TextView textEmptyList;
    @BindView(R.id.main_progress)
    ProgressBar progressBar;
    private PublishAdapterRv publishAdapterRv;
    private int currentPage = PAGE_START;
    private boolean isLoading = false, isLastPage = false;

    @InjectPresenter
    MainPresenter mainPresenter;

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
                // Задержка просто для показа загрузки
                new Handler().postDelayed(() -> mainPresenter.initNextPage(publishAdapterRv.getItemCount()), 500);
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
        if (isNetworkConnected()) {
            mainPresenter.initFirstPage();
        } else {
            showMesage(R.string.error_network);
        }
    }

    public void loadFirstPage(List<PublishModel> publishModels) {
        publishAdapterRv.clear();
        progressBar.setVisibility(View.GONE);
        publishAdapterRv.addAll(publishModels);
        if (publishAdapterRv.getItemCount() == LOAD_ITEM_SIZE) {
            publishAdapterRv.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }

    public void loadNextPage(List<PublishModel> publishModels) {
        publishAdapterRv.removeLoadingFooter();
        isLoading = false;
        publishAdapterRv.addAll(publishModels);
        if (publishAdapterRv.getItemCount() == LOAD_ITEM_SIZE * currentPage) {
            publishAdapterRv.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }

    @Override
    public void showMesage(int resource) {
        Toast.makeText(this, resource, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupEmptyList() {
        recyclerView.setVisibility(View.GONE);
        textEmptyList.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.float_button)
    void onSaveClick() {
        startActivity(new Intent(MainActivity.this, PublishActivity.class));
    }
}
