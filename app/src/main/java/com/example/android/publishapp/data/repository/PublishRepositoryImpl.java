package com.example.android.publishapp.data.repository;

import com.example.android.publishapp.data.model.Api;
import com.example.android.publishapp.data.model.PublishModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Call;

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

    @Override
    public Single<Map<String, PublishModel>> getPublishModelList() {
        return api.getAllPublishes();
    }

    @Override
    public Call<List<PublishModel>> getPublishesCall() {
        return api.getAllPublishesCall();
    }
}
