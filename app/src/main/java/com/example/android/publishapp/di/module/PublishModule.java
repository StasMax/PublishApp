package com.example.android.publishapp.di.module;

import com.example.android.publishapp.data.model.Api;
import com.example.android.publishapp.data.repository.DatabaseRepositoryImpl;
import com.example.android.publishapp.data.repository.IDatabaseRepository;
import com.example.android.publishapp.data.repository.IPublishRepository;
import com.example.android.publishapp.data.repository.PublishRepositoryImpl;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.domain.iteractor.PublishIteractorImpl;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.example.android.publishapp.presentation.app.App.getRetrofit;

@Module
public class PublishModule {
    @Provides
    @Singleton
    IPublishIteractor publishIteractor(IPublishRepository publishRepository, IDatabaseRepository databaseRepository) {
        return new PublishIteractorImpl(publishRepository, databaseRepository);
    }

    @Provides
    @Singleton
    IPublishRepository publishRepository(Api api) {
        return new PublishRepositoryImpl(api);
    }

    @Provides
    @Singleton
    IDatabaseRepository databaseRepository(DatabaseReference databaseReference, StorageReference storageReference) {return new DatabaseRepositoryImpl(databaseReference, storageReference);
    }

    @Provides
    @Singleton
    Api getApi() {
        return getRetrofit().create(Api.class);
    }

    @Provides
    @Singleton
    StorageReference storageReference(){return FirebaseStorage.getInstance().getReference("images");}

    @Provides
    @Singleton
    DatabaseReference databaseReference(){return FirebaseDatabase.getInstance().getReference();}
}
