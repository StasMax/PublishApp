package com.example.android.publishapp.data.repository;

import com.example.android.publishapp.data.model.PublishModel;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.Call;

public interface IPublishRepository {

    Single<PublishModel> insertPublishModel(PublishModel publishModel);

    Call<Map<String, PublishModel>> getPublishModelListCallback(int page, int pageSize);
}
