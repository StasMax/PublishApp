package com.example.android.publishapp.presentation.mvp.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.MvpView;
import com.example.android.publishapp.data.model.PublishModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import static android.support.constraint.Constraints.TAG;
import static com.example.android.publishapp.presentation.Constant.FIREBASE_DATABASE_LOCATION_MODEL;
import static com.example.android.publishapp.presentation.Logger.logErrorDatabase;

public class CommonFieldsPresenter<View extends MvpView> extends BasePresenter<View> {
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

    private DatabaseReference databaseReference;

    @Inject
    public CommonFieldsPresenter(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
        initFieldId();
    }

    public CommonFieldsPresenter() {
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
        Query query = databaseReference
                .child(FIREBASE_DATABASE_LOCATION_MODEL)
                .orderByChild("id")
                .limitToLast(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    long idLast = (userSnapshot.getValue(PublishModel.class)).getId();
                    setupId(idLast);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                logErrorDatabase(databaseError.getMessage());
            }
        });
    }

    private void setupId(long idLast) {
        setLastId(++idLast);
    }
}
