package com.example.android.publishapp.data.repository;

import com.example.android.publishapp.data.model.Api;
import com.example.android.publishapp.data.model.PublishModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class PublishRepositoryImpl implements IPublishRepository {
    private Api api;

    @Inject
    public PublishRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public void insertPublishModel(PublishModel publishModel) {
        api.setPublish(publishModel);
    }

    @Override
    public Single<List<PublishModel>> getPublishModelList() {
        return api.getAllPublishes();
    }

    @Override
    public Single<PublishModel> getPublishmodelByDate(String date) {
        return api.getPublishDate(date);
    }
}
