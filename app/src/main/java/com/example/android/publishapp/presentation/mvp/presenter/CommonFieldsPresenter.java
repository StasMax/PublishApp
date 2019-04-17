package com.example.android.publishapp.presentation.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.MvpView;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.presentation.mvp.view.PublishView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import static android.support.constraint.Constraints.TAG;
import static com.example.android.publishapp.presentation.Constant.FIREBASE_DATABASE_LOCATION_MODEL;
import static com.example.android.publishapp.presentation.app.App.getDatabaseReference;

public class CommonFieldsPresenter<View extends MvpView> extends BasePresenter<PublishView> {
    @Setter
    @Getter
    private long lastId;
    @Getter
    private List<String> categories = new ArrayList<>();
    @Getter
    private List<String> tags = new ArrayList<>();
    @Getter
    private String header = null;
    @Getter
    private String description = null;
    @Getter
    private List<String> links = new ArrayList<>();
    @Getter
    private List<String> linksNames = new ArrayList<>();

    CommonFieldsPresenter() {
        initFieldId();
    }

    public void fieldCategory(String category) {
        String[] linkSplit = category.split(", ");
        categories = Arrays.asList(linkSplit);
    }

    public void fieldTag(String tag) {
        String[] linkSplit = tag.split(", ");
        tags = Arrays.asList(linkSplit);
    }

    public void fieldHeader(String headerField) {
        header = headerField;
    }

    public void fieldDescription(String descriptionField) {
        description = descriptionField;
    }

    public void fieldLink(String link) {
        String[] linkSplit = link.split(", ");
        links = Arrays.asList(linkSplit);
    }

    public void fieldLinkName(String linkName) {
        String[] linkSplit = linkName.split(", ");
        linksNames = Arrays.asList(linkSplit);
    }

    private void initFieldId() {
        Query query = getDatabaseReference()
                .child(FIREBASE_DATABASE_LOCATION_MODEL)
                .orderByChild("id")
                .limitToLast(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    long idLast = (userSnapshot.getValue(PublishModel.class)).getId();
                    setupId(idLast);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    private void setupId(long idLast) {
        setLastId(idLast + 1);
    }
}
