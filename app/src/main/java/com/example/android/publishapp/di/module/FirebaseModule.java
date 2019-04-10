package com.example.android.publishapp.di.module;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {

    @Provides
    @Singleton
    FirebaseStorage storage() {
        return FirebaseStorage.getInstance();
    }

    @Provides
    @Singleton
    StorageReference storageReference(FirebaseStorage storage) {
        return storage.getReference("images");
    }
}
