package com.example.android.publishapp.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.MainView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.android.publishapp.presentation.Constant.FIREBASE_DATABASE_LOCATION_MODEL;
import static com.example.android.publishapp.presentation.Constant.LOAD_ITEM_SIZE;
import static com.example.android.publishapp.presentation.app.App.getDatabaseReference;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {
    private List<PublishModel> modelList = new ArrayList<>();
    private IPublishIteractor publishIteractor;

    @Inject
    public MainPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public void initNextPage(int idCount) {
        List<PublishModel> models = new ArrayList<>();
        Query query = getDatabaseReference()
                .child(FIREBASE_DATABASE_LOCATION_MODEL)
                .orderByChild("id")
                .startAt(idCount)
                .limitToFirst(LOAD_ITEM_SIZE);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    models.add(userSnapshot.getValue(PublishModel.class));
                }
                getViewState().loadNextPage(models);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                getViewState().showMesage(R.string.error_load);
            }
        });
    }

    public void initFirstPage() {
        List<PublishModel> models = new ArrayList<>();
        Query query = getDatabaseReference()
                .child(FIREBASE_DATABASE_LOCATION_MODEL)
                .orderByKey()
                .limitToFirst(LOAD_ITEM_SIZE);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    models.add(userSnapshot.getValue(PublishModel.class));
                }
                if (models.size() == 0) {
                    getViewState().setupEmptyList();
                } else {
                    getViewState().loadFirstPage(models);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                getViewState().showMesage(R.string.error_load);
            }
        });
    }
}