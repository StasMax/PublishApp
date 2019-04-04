package com.example.android.publishapp.data.model;

import com.example.android.publishapp.data.model.PublishModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @GET("/posts/{date}")
    Single<PublishModel> getPublishDate(@Query("date") String date);

    @GET("/posts/")
    Single<List<PublishModel>> getAllPublishes();

    @POST("/posts/new.json")
    Single<PublishModel> setPublish(@Body PublishModel publishModel);
}
