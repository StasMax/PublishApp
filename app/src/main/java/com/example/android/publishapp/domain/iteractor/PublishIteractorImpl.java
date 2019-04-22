package com.example.android.publishapp.domain.iteractor;

import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.data.repository.IDatabaseRepository;
import com.example.android.publishapp.data.repository.IPublishRepository;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class PublishIteractorImpl implements IPublishIteractor {
    private IPublishRepository publishRepository;
    private IDatabaseRepository databaseRepository;

    @Inject
    public PublishIteractorImpl(IPublishRepository publishRepository, IDatabaseRepository databaseRepository) {
        this.publishRepository = publishRepository;
        this.databaseRepository = databaseRepository;
    }

    @Override
    public Single<PublishModel> insertPostInDb(PublishModel publishModel) {
        return publishRepository.insertPublishModel(publishModel);
    }

    @Override
    public Single<List<PublishModel>> getFirstModels() {
        return databaseRepository.getFirstPublishModels();
    }

    @Override
    public Single<List<PublishModel>> getNextModels(long lastId) {
        return databaseRepository.getNextPublishModels(lastId);
    }

    @Override
    public StorageReference getStorageReference() {
        return databaseRepository.getImageStorageReference();
    }
}
