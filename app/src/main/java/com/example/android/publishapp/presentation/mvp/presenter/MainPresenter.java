package com.example.android.publishapp.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import com.example.android.publishapp.data.repository.IDatabaseRepository;
import com.example.android.publishapp.presentation.mvp.view.MainView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {
    private IDatabaseRepository databaseRepository;

    @Inject
    public MainPresenter(IDatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public void initNextPage(int idCount) {
        disposeBag(databaseRepository.getNextPublishModels(idCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(publishModels -> getViewState().loadNextPage(publishModels)));

    }

    public void initFirstPage() {
        disposeBag(databaseRepository.getFirstPublishModels()
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