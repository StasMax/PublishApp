package com.example.android.publishapp.data.repository;

import android.support.annotation.NonNull;

import com.example.android.publishapp.data.model.PublishModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.example.android.publishapp.presentation.Constant.FIREBASE_DATABASE_LOCATION_MODEL;
import static com.example.android.publishapp.presentation.Constant.LOAD_ITEM_SIZE;
import static com.example.android.publishapp.presentation.Logger.logErrorDatabase;

public class DatabaseRepositoryImpl implements IDatabaseRepository {

    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    @Inject
    public DatabaseRepositoryImpl(DatabaseReference databaseReference, StorageReference storageReference) {
        this.databaseReference = databaseReference;
        this.storageReference = storageReference;
    }

    @Override
    public Single<List<PublishModel>> getFirstPublishModels() {
        List<PublishModel> models = new ArrayList<>();
        Query query = databaseReference
                .child(FIREBASE_DATABASE_LOCATION_MODEL)
                .orderByKey()
                .limitToFirst(LOAD_ITEM_SIZE);
        return Single.create(subscriber -> query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    models.add(userSnapshot.getValue(PublishModel.class));
                }
                subscriber.onSuccess(models);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                logErrorDatabase(databaseError.getMessage());
            }
        }));
    }

    @Override
    public Single<List<PublishModel>> getNextPublishModels(long lastId) {
        List<PublishModel> models = new ArrayList<>();
        Query query = databaseReference
                .child(FIREBASE_DATABASE_LOCATION_MODEL)
                .orderByChild("id")
                .startAt(lastId)
                .limitToFirst(LOAD_ITEM_SIZE);
        return Single.create(subscriber -> query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    models.add(userSnapshot.getValue(PublishModel.class));
                }
                subscriber.onSuccess(models);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                logErrorDatabase(databaseError.getMessage());
            }
        }));
    }

    @Override
    public StorageReference getImageStorageReference() {
        return storageReference.child("images/" + UUID.randomUUID().toString());
    }
}
