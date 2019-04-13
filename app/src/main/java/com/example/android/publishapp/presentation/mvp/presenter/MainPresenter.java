package com.example.android.publishapp.presentation.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.MainView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.publishapp.presentation.Constant.LOAD_ITEM_SIZE;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {
    private List<PublishModel> modelList = new ArrayList<>();
    private IPublishIteractor publishIteractor;

    @Inject
    public MainPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public void initPublishers() {
        disposeBag(publishIteractor.getAllPostsFromDb()
                .doOnSubscribe(disposable -> modelList.clear())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::initPublishersRecycle, Throwable::printStackTrace));
    }

    public void initPublishersRecycle(Map<String, PublishModel> publishModels) {
        modelList.clear();
        modelList.addAll(publishModels.values());
        if (publishModels.size() == 0) {
            getViewState().setupEmptyList();
        }
        getViewState().setupPublishList();
        getViewState().loadFirstPage();
    }

    public List<PublishModel> initList(int itemCount) {
        List<PublishModel> models = new ArrayList<>();
        for (int i = itemCount; i < itemCount + LOAD_ITEM_SIZE && i < modelList.size(); i++) {
            models.add(modelList.get(i));
        }
        return models;
    }
}