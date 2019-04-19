package com.example.android.publishapp.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.android.publishapp.data.model.PublishModel;

import java.util.List;

@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface MainView extends MvpView {

    void setupEmptyList();

    void loadFirstPage(List<PublishModel> publishModels);

    void loadNextPage(List<PublishModel> publishModels);

    void showMessage(int resource);

    void setupEndList();
}