package com.example.android.publishapp.domain.iteractor;

import com.example.android.publishapp.data.model.PublishModel;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.Call;

public interface IPublishIteractor {

    Single<PublishModel> insertPostInDb(PublishModel publishModel);

    Call<Map<String, PublishModel>> getAllPostsFromDbCallback(int page, int pageSize);
}
