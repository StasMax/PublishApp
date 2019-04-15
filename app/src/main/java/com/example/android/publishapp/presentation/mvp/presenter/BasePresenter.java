package com.example.android.publishapp.presentation.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import lombok.Getter;

import static android.app.Activity.RESULT_OK;
import static com.example.android.publishapp.presentation.Constant.PICK_IMAGE;
import static com.example.android.publishapp.presentation.app.App.getStorageReference;

public class BasePresenter<View extends MvpView> extends MvpPresenter<View> {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Getter
    private List<String> fileImage = new ArrayList<>();

    void disposeBag(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private void unsubscribe() {
        compositeDisposable.clear();
        compositeDisposable = new CompositeDisposable();
    }

    public void initUploadImage(int requestCode, int resultCode, Intent data, Context context) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            if (filePath != null) {
                final ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();
                StorageReference ref = getStorageReference().child("images/" + UUID.randomUUID().toString());
                ref.putFile(filePath)
                        .addOnSuccessListener(taskSnapshot -> {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnCompleteListener(task -> fileImage.add(task.getResult().toString()));
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }
}
