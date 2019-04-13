package com.example.android.publishapp.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.android.publishapp.data.model.PublishModel;

import java.util.List;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface MainView extends MvpView {

    void setupEmptyList();

    void setupPublishList();

    void loadFirstPage(List<PublishModel> publishModels);

    void loadNextPage(List<PublishModel> publishModels);
}
