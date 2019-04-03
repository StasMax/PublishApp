package com.example.android.publishapp.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.presentation.Provider;
import com.example.android.publishapp.presentation.mvp.view.MainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView>{
    Provider mProvider = new Provider();
@Inject
    public MainPresenter() {
    }

    public void initPublishers() {
        List<PublishModel>listModel = new ArrayList<>();
       listModel.addAll(mProvider.listProvider());
       if (listModel.size() == 0){
           getViewState().setupEmptyList();
       }
        getViewState().setupPublishList(listModel);
    }
}
