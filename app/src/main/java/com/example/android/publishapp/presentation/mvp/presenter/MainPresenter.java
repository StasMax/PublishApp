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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.publishapp.presentation.Constant.LOAD_ITEM_SIZE;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {
    private List<PublishModel> modelList = new ArrayList<>();
    final int batchSize = 10;
    private IPublishIteractor publishIteractor;

    @Inject
    public MainPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

  /*  public void initPublishers() {
        disposeBag(publishIteractor.getAllPostsFromDb()
                .doOnSubscribe(disposable -> modelList.clear())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::initPublishersRecycle, Throwable::printStackTrace));
    }*/

    public void initPublishersRecycle(Map<String, PublishModel> publishModels) {
        modelList.clear();
        modelList.addAll(publishModels.values());

        if (publishModels.size() == 0) {
            getViewState().setupEmptyList();
        }
        getViewState().setupPublishList();
            }

    public void initNextPage(int currentPage) {
        List<PublishModel> models = new ArrayList<>();
       publishIteractor.getAllPostsFromDb(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Map<String, PublishModel>>() {
                    @Override
                    public void onSuccess(Map<String, PublishModel> stringPublishModelMap) {
                        models.addAll(stringPublishModelMap.values());
                        getViewState().loadNextPage(models);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void initFirstPage(int currentPage) {
        List<PublishModel> models = new ArrayList<>();
        disposeBag(publishIteractor.getAllPostsFromDb(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(publishModels -> {
                    models.addAll(publishModels.values());
                    getViewState().loadFirstPage(models);
                }, Throwable::printStackTrace));



    }
}