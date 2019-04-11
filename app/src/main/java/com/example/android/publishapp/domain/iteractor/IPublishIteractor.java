package com.example.android.publishapp.domain.iteractor;

import com.example.android.publishapp.data.model.PublishModel;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface IPublishIteractor {

    Single<PublishModel> insertPostInDb(PublishModel publishModel);

    Flowable<Map<String, PublishModel>> getAllPostsFromDb();
}
