package com.example.android.publishapp.domain.iteractor;

import com.example.android.publishapp.data.model.PublishModel;

import java.util.Map;

import io.reactivex.Single;

public interface IPublishIteractor {

    Single<PublishModel> insertPostInDb(PublishModel publishModel);

    Single<Map<String, PublishModel>> getAllPostsFromDb(int pageIndex);
}
