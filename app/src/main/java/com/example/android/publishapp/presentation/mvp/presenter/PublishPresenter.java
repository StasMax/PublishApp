package com.example.android.publishapp.presentation.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.PublishView;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static com.example.android.publishapp.presentation.mvp.ui.fragment.PostFragment.PICK_IMAGE;


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
            PublishModel publishModel = new PublishModel(categories, tags, header, description, filePicture, links, linksNames, date, type);
            disposeBag(publishIteractor.insertPostInCloud(publishModel)
                    .doFinally(this::clearObjects)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }
    }

    public void initUploadImage(int requestCode, int resultCode, Intent data, StorageReference storageReference, Context context) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            if (filePath != null) {
                final ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();
                StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
                ref.putFile(filePath)
                        .addOnSuccessListener(taskSnapshot -> {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnCompleteListener(task -> filePicture.add(task.getResult().toString()));
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

            }
        }
    }

    private void clearObjects() {
        filePicture.clear();
        links.clear();
        linksNames.clear();
    }
}
