package com.example.android.publishapp.presentation.adapter;

import android.support.annotation.NonNull;

import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublishPositionalDataSource extends android.arch.paging.PositionalDataSource<PublishModel> {

    private IPublishIteractor publishIteractor;

    @Inject
    public PublishPositionalDataSource(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<PublishModel> callback) {

        Call<Map<String, PublishModel>> call = publishIteractor.getAllPostsFromDbCallback(params.requestedStartPosition, params.requestedLoadSize);
        call.enqueue(new Callback<Map<String, PublishModel>>() {
            @Override
            public void onResponse(Call<Map<String, PublishModel>> call, Response<Map<String, PublishModel>> response) {
                List<PublishModel> result = new ArrayList<>();
                if (response.body() != null) {
                    result.addAll(response.body().values());
                    Collections.sort(result, COMPARE_BY_TYPE);
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

    public Comparator<PublishModel> COMPARE_BY_TYPE = (o1, o2) -> {
        if (o1.getType() == 1 && o2.getType() != 1) {
            return -1;
        } else if (o1.getType() != 1 && o2.getType() == 1) {
            return 1;
        }
        return 0;
    };
}
