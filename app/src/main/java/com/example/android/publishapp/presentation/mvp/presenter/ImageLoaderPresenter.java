package com.example.android.publishapp.presentation.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.arellomobile.mvp.MvpView;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.PublishView;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import lombok.Getter;

import static android.app.Activity.RESULT_OK;
import static com.example.android.publishapp.presentation.Constant.PICK_IMAGE;

public class ImageLoaderPresenter<View extends MvpView> extends BasePresenter<View> {
    @Getter
    private List<String> fileImage = new ArrayList<>();
    private IPublishIteractor publishIteractor;

    @Inject
    public ImageLoaderPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public ImageLoaderPresenter() {
    }

    public void initUploadImage(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            if (filePath != null) {
               /* final ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();*/
                StorageReference ref = publishIteractor.getStorageReference();
                ref.putFile(filePath)
                        .addOnSuccessListener(taskSnapshot -> {
                          //  progressDialog.dismiss();
                            ref.getDownloadUrl().addOnCompleteListener(task -> fileImage.add(task.getResult().toString()));
                      //      Toast.makeText(context, "Uploaded", Toast.LENGTH_LONG).show();
                        })
                        .addOnFailureListener(e -> {
                  //          progressDialog.dismiss();
                   //         Toast.makeText(context, "Failed " + e.getMessage(), Toast.LENGTH_LONG).show();
                        })
                        .addOnProgressListener(taskSnapshot -> {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                    //        progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        });
            }
        }
    }
}
