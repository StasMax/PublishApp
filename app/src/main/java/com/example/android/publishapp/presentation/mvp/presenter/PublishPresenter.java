package com.example.android.publishapp.presentation.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.ui.PublishActivity;
import com.example.android.publishapp.presentation.mvp.view.PublishView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static com.example.android.publishapp.presentation.mvp.ui.PublishActivity.PICK_IMAGE;

@InjectViewState
public class PublishPresenter extends BasePresenter<PublishView> {
    private IPublishIteractor publishIteractor;
    private String[] categories = null;
    private String[] tags = null;
    private String header = null;
    private String description = null;
    private List<String> filePicture = new ArrayList<>();
    private List<String> links = new ArrayList<>();
    private List<String> linksNames = new ArrayList<>();
    private final String date = null;
    private final int type = 0;
    private PublishModel publishModel;
    private Uri downloadUrl;
    private Uri filePath;

    @Inject
    public PublishPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public void fieldCategory(String category) {
        categories = category.split(", ");
    }

    public void fieldTag(String tag) {
        tags = tag.split(", ");
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

    public void initSendPost() {

        if (categories == null || tags == null || links.size() != linksNames.size()) {
            getViewState().showError(R.string.error_fields);
        } else {
            publishModel = new PublishModel(categories, tags, header, description, filePicture, links, linksNames, date, type);
            disposeBag(publishIteractor.insertPostInCloud(publishModel)
                    .doFinally(this::clearObjects)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }
    }

    public void uploadImage(Context context, Uri filePath, StorageReference storageReference) {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(taskSnapshot -> {
                        progressDialog.dismiss();

                        Toast.makeText(context, "Uploaded", Toast.LENGTH_LONG).show();
                    })
                    .addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Failed " + e.getMessage(), Toast.LENGTH_LONG).show();
                    })
                    .addOnProgressListener(taskSnapshot -> {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        progressDialog.setMessage("Uploaded " + (int) progress + "%");
                    });
            ref.getDownloadUrl().addOnCompleteListener(task -> filePicture.add(task.getResult().toString()));
        }
    }

    public void initUploadImage(int requestCode, int resultCode, Intent data, StorageReference storageReference, Context context) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            uploadImage(context, filePath, storageReference);
        }
    }

    private void clearObjects() {
        filePicture.clear();
        links.clear();
        linksNames.clear();
    }
}
