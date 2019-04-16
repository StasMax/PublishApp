package com.example.android.publishapp.data.repository;

import android.util.Log;

import com.example.android.publishapp.data.model.PublishModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static com.example.android.publishapp.presentation.Constant.DATABASE;
import static com.example.android.publishapp.presentation.Constant.FIREBASE_DATABASE_LOCATION_MODEL;
import static com.example.android.publishapp.presentation.Constant.LOAD_ITEM_SIZE;
import static com.example.android.publishapp.presentation.app.App.getDatabaseReference;

public class DatabaseRepositoryImpl implements IDatabaseRepository {

    @Override
    public Single<List<PublishModel>> getFirstPublishModels() {
        List<PublishModel> models = new ArrayList<>();
        Query query = getDatabaseReference()
                .child(FIREBASE_DATABASE_LOCATION_MODEL)
                .orderByKey()
                .limitToFirst(LOAD_ITEM_SIZE);
        return Single.create(subscriber -> query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    models.add(userSnapshot.getValue(PublishModel.class));
                }
                subscriber.onSuccess(models);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(DATABASE, databaseError.getMessage());
            }
        }));
    }

    @Override
    public Single<List<PublishModel>> getNextPublishModels(long lastId) {
        List<PublishModel> models = new ArrayList<>();
        Query query = getDatabaseReference()
                .child(FIREBASE_DATABASE_LOCATION_MODEL)
                .orderByChild("id")
                .startAt(lastId)
                .limitToFirst(LOAD_ITEM_SIZE);
        return Single.create(subscriber -> query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    models.add(userSnapshot.getValue(PublishModel.class));
                }
                subscriber.onSuccess(models);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(DATABASE, databaseError.getMessage());
            }
        }));
    }
}
