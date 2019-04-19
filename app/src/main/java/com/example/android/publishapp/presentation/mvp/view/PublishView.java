package com.example.android.publishapp.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface PublishView extends MvpView {

    void showMessage(int resource);

    void showProgressDialog();

    void hideProgressDialog();

    void setProgressDialog(String text);
}
