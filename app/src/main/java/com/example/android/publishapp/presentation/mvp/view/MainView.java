package com.example.android.publishapp.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.android.publishapp.data.model.PublishModel;

import java.util.List;

@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface MainView extends MvpView {

    void showError(int textResource);

    void setupEmptyList();

    void setupPublishList(List<PublishModel> publishList);
}
