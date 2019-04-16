package com.example.android.publishapp.data.repository;

import com.example.android.publishapp.data.model.PublishModel;

import java.util.List;

import io.reactivex.Single;

public interface IDatabaseRepository {
    Single<List<PublishModel>> getFirstPublishModels();

    Single<List<PublishModel>> getNextPublishModels(long lastId);
}
