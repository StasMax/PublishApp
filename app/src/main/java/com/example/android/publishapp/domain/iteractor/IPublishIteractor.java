package com.example.android.publishapp.domain.iteractor;

import com.example.android.publishapp.data.model.PublishModel;

import java.util.List;

import io.reactivex.Single;

public interface IPublishIteractor {

    Single<PublishModel> insertPostInDb(PublishModel publishModel);

    Single<List<PublishModel>>getFirstModels();

    Single<List<PublishModel>>getNextModels(long lastId);
}
