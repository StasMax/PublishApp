package com.example.android.publishapp.data.repository;

import com.example.android.publishapp.data.model.PublishModel;

import io.reactivex.Single;

public interface IPublishRepository {

    Single<PublishModel> insertPublishModel(PublishModel publishModel);

}
