package com.example.android.publishapp.data.repository;

import com.example.android.publishapp.data.model.PublishModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.Call;

public interface IPublishRepository {

    Single<PublishModel> insertPublishModel(PublishModel publishModel);

    Single<Map<String, PublishModel>> getPublishModelList();

Call<List<PublishModel>>getPublishesCall();
}
