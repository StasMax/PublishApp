package com.example.android.publishapp.data.repository;

import retrofit2.http.GET;

public interface Api {
    @GET("rss")
    Single<NewsModelRSS> getNews();
}
