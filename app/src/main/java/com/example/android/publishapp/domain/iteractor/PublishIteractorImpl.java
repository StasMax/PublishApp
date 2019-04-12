package com.example.android.publishapp.domain.iteractor;

import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.data.repository.IPublishRepository;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class PublishIteractorImpl implements IPublishIteractor {
    private IPublishRepository publishRepository;

    @Inject
    public PublishIteractorImpl(IPublishRepository publishRepository) {
        this.publishRepository = publishRepository;
    }

    @Override
    public Single<PublishModel> insertPostInDb(PublishModel publishModel) {
        return publishRepository.insertPublishModel(publishModel);
    }

    @Override
    public Single<Map<String, PublishModel>> getAllPostsFromDb() {
        return publishRepository.getPublishModelList();
    }
}
