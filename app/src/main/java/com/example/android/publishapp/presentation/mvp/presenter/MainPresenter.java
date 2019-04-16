package com.example.android.publishapp.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.presentation.mvp.view.MainView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.publishapp.presentation.Constant.FIREBASE_DATABASE_LOCATION_MODEL;
import static com.example.android.publishapp.presentation.Constant.LOAD_ITEM_SIZE;
import static com.example.android.publishapp.presentation.app.App.getDatabaseReference;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    public void initNextPage(int idCount) {


    }

    public void initFirstPage() {

    }
}