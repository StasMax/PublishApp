package com.example.android.publishapp.domain.iteractor;

import com.example.android.publishapp.data.model.PublishModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IPublishIteractor {

    Completable insertPostInCloud(PublishModel publishModel);

    Single<List<PublishModel>>getAllPostsFromCloud();

    Single<PublishModel>getEventByDate(String date);
}
