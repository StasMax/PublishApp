package com.example.android.publishapp.di.module;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.adapter.PublishDiffUtilCallback;
import com.example.android.publishapp.presentation.adapter.PublishSourceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {PublishModule.class})
public class PagerModule {

    @Provides
    @Singleton
    PublishSourceFactory sourceFactory(IPublishIteractor publishIteractor) {
        return new PublishSourceFactory(publishIteractor);
    }

    @Provides
    @Singleton
    PagedList.Config config() {
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(5)
                .build();
    }

    @Provides
    @Singleton
    LiveData<PagedList<PublishModel>> pagedListLiveData(PublishSourceFactory sourceFactory, PagedList.Config config) {
        return new LivePagedListBuilder<>(sourceFactory, config)
                .build();
    }

    @Provides
    @Singleton
    PublishDiffUtilCallback publishDiffUtil(){return new PublishDiffUtilCallback();}
}
