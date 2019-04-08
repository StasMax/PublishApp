package com.example.android.publishapp.presentation.adapter;

import android.arch.paging.DataSource;

import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;

public class PublishSourceFactory extends DataSource.Factory<String, PublishModel> {

    private final IPublishIteractor publishIteractor;

    public PublishSourceFactory(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    @Override
    public DataSource create() {
        return new PublishPositionalDataSource(publishIteractor);
    }
}
