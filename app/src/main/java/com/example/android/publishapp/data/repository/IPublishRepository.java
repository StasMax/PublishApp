package com.example.android.publishapp.data.repository;

import com.example.android.publishapp.data.model.PublishModel;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface IPublishRepository {

    Single<PublishModel> insertPublishModel(PublishModel publishModel);

    Single<Map<String, PublishModel>> getPublishModelList(int pageIndex);
}
