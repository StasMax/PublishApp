package com.example.android.publishapp.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.MainView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {
    private IPublishIteractor publishIteractor;

    @Inject
    public MainPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public void initNextPage(int idCount) {
        disposeBag(publishIteractor.getNextModels(idCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(publishModels -> {
                    if (publishModels.size() == 0) {
                        getViewState().setupEndList();
                    } else {
                        getViewState().loadNextPage(publishModels);
                    }
                }));
    }

    public void initFirstPage() {
        disposeBag(publishIteractor.getFirstModels()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(publishModels -> {
                    if (publishModels.size() == 0) {
                        getViewState().setupEmptyList();
                    } else {
                        getViewState().loadFirstPage(publishModels);
                    }
                }));
    }
}
