package com.example.android.publishapp.data.repository;

import com.example.android.publishapp.data.model.PublishModel;

import java.util.List;

import io.reactivex.Single;

public interface IPublishRepository {

    void insertPublishModel(PublishModel publishModel);

    Single<List<PublishModel>> getPublishModelList();

    Single<PublishModel> getPublishmodelByDate(String date);
}
