package com.example.android.publishapp.presentation.adapter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class PublishPositionalDataSource extends android.arch.paging.PositionalDataSource<PublishModel> {

    private IPublishIteractor publishIteractor;

    @Inject
    public PublishPositionalDataSource(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<PublishModel> callback) {
        Log.d(TAG, "loadInitial, requestedStartPosition = " + params.requestedStartPosition +
                ", requestedLoadSize = " + params.requestedLoadSize);

        Call<Map<String, PublishModel>> call = publishIteractor.getAllPostsFromDbCallback(params.requestedStartPosition, params.requestedLoadSize);
        call.enqueue(new Callback<Map<String, PublishModel>>() {
            @Override
            public void onResponse(Call<Map<String, PublishModel>> call, Response<Map<String, PublishModel>> response) {
                List<PublishModel> result = new ArrayList<>();
                if (response.body() != null) {
                    result.addAll(response.body().values());
                }
                if (params.placeholdersEnabled) {
                    callback.onResult(result, 0, result.size());
                } else {
                    callback.onResult(result, 0);
                }
            }

            @Override
            public void onFailure(Call<Map<String, PublishModel>> call, Throwable t) {

            }
        });


    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<PublishModel> callback) {
        Log.d(TAG, "loadRange, startPosition = " + params.startPosition + ", loadSize = " + params.loadSize);
        Call<Map<String, PublishModel>> call = publishIteractor.getAllPostsFromDbCallback(params.startPosition, params.loadSize);
        call.enqueue(new Callback<Map<String, PublishModel>>() {
            @Override
            public void onResponse(Call<Map<String, PublishModel>> call, Response<Map<String, PublishModel>> response) {
                List<PublishModel> result = new ArrayList<>();
                if (response.body() != null) {
                    result.addAll(response.body().values());
                }
                callback.onResult(result);
            }

            @Override
            public void onFailure(Call<Map<String, PublishModel>> call, Throwable t) {

            }
        });
    }

}
