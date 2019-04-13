package com.example.android.publishapp.data.model;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("/post/newPost")
    Single<Map<String, PublishModel>> getAllPublishes(@Query("page") int pageIndex);

    @POST("post/{new}.json")
    Single<PublishModel> setPublish(@Path("new") String s1, @Body PublishModel publishModel);
}
