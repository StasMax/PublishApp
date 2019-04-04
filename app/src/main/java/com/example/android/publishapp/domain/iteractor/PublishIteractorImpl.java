package com.example.android.publishapp.domain.iteractor;

import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.data.repository.IPublishRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class PublishIteractorImpl implements IPublishIteractor {
    private IPublishRepository publishRepository;

    @Inject
    public PublishIteractorImpl(IPublishRepository publishRepository) {
        this.publishRepository = publishRepository;
    }

    @Override
    public Completable insertPostInCloud(PublishModel publishModel) {
        return Completable.fromAction(() -> publishRepository.insertPublishModel(publishModel));
    }

    @Override
    public Single<List<PublishModel>> getAllPostsFromCloud() {
        return publishRepository.getPublishModelList();
    }

    @Override
    public Single<PublishModel> getEventByDate(String date) {
        return publishRepository.getPublishmodelByDate(date);
    }
}
