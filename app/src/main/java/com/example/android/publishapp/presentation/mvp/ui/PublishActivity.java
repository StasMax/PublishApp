package com.example.android.publishapp.presentation.mvp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android.publishapp.R;
import com.example.android.publishapp.presentation.app.App;
import com.example.android.publishapp.presentation.mvp.presenter.PublishPresenter;
import com.example.android.publishapp.presentation.mvp.view.PublishView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class PublishActivity extends MvpAppCompatActivity implements PublishView {
    @BindView(R.id.edit_category_post)
    EditText editCategoryText;
    @BindView(R.id.edit_tag_post)
    EditText editTagText;
    @BindView(R.id.button_image)
    Button buttonImage;

    public static final int PICK_IMAGE = 1;
    FirebaseStorage storage;
    StorageReference storageReference;
    private FirebaseApp app;

    @Inject
    @InjectPresenter
    PublishPresenter publishPresenter;

    @ProvidePresenter
    PublishPresenter providePresenter() {
        return publishPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        app = FirebaseApp.getInstance();
        storage = FirebaseStorage.getInstance(app);
        storageReference = storage.getReference("images");
    }


    @OnTextChanged(R.id.edit_category_post)
    public void onCategoryTextChanged(CharSequence s, int start, int before, int count) {
        publishPresenter.fieldCategory(s.toString());
    }

    @OnTextChanged(R.id.edit_tag_post)
    public void onTagTextChanged(CharSequence s, int start, int before, int count) {
        publishPresenter.fieldTag(s.toString());
    }

    @OnTextChanged(R.id.edit_header_post)
    public void onHeaderTextChanged(CharSequence s, int start, int before, int count) {
        publishPresenter.fieldHeader(s.toString());
    }

    @OnTextChanged(R.id.edit_description_post)
    public void onDescriptionTextChanged(CharSequence s, int start, int before, int count) {
        publishPresenter.fieldDescription(s.toString());
    }

    @OnTextChanged(R.id.edit_link_post)
    public void onLinkTextChanged(CharSequence s, int start, int before, int count) {
        publishPresenter.fieldLink(s.toString());
    }

    @OnTextChanged(R.id.edit_link_post_name)
    public void onLinkNameTextChanged(CharSequence s, int start, int before, int count) {
        publishPresenter.fieldLinkName(s.toString());
    }

    @Override
    public void showError(int resource) {
        Toast.makeText(this, resource, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button_send_post)
    void onClickPost() {
        publishPresenter.initSendPost();
    }

    @OnClick(R.id.button_image)
    void onClickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        publishPresenter.initUploadImage(requestCode, resultCode, data, storageReference, this);

    }
}


