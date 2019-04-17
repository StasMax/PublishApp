package com.example.android.publishapp.data.repository;

import com.example.android.publishapp.data.model.Api;
import com.example.android.publishapp.data.model.PublishModel;

import javax.inject.Inject;

import io.reactivex.Single;

public class PublishRepositoryImpl implements IPublishRepository {
    private Api api;

    @Inject
    public PublishRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<PublishModel> insertPublishModel(PublishModel publishModel) {
        return api.setPublish("newPost", publishModel);
    }
}
