package com.example.android.publishapp.presentation.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.PublishView;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static com.example.android.publishapp.presentation.Constant.PICK_IMAGE;
import static com.example.android.publishapp.presentation.Constant.TYPE_EVENT;

@InjectViewState
public class EventPresenter extends CommonFieldsPresenter<PublishView> {
    private List<String> fileImage = new ArrayList<>();
    private IPublishIteractor publishIteractor;

    @Inject
    public EventPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public void initSendEvent() {
        if (categories == null || tags == null || links.size() != linksNames.size()) {
            getViewState().showMessage(R.string.error_fields);
        } else {
            PublishModel publishModel = PublishModel.builder()
                    .id(lastId)
                    .category(categories)
                    .tag(tags)
                    .header(header)
                    .description(description)
                    .imageFile(fileImage)
                    .link(links)
                    .linkName(linksNames)
                    .date(initDate())
                    .typeViewHolder(TYPE_EVENT)
                    .build();

            disposeBag(publishIteractor.insertPostInDb(publishModel)
                    .doAfterSuccess(publishModel12 -> fileImage.clear())
                    .doOnSuccess(publishModel1 -> getViewState().showMessage(R.string.success_post))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }
    }

    public void initUploadImage(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            if (filePath != null) {
                getViewState().showProgressDialog();
                StorageReference ref = publishIteractor.getStorageReference();
                ref.putFile(filePath)
                        .addOnSuccessListener(taskSnapshot -> {
                            getViewState().hideProgressDialog();
                            ref.getDownloadUrl().addOnCompleteListener(task -> fileImage.add(task.getResult().toString()));
                            getViewState().showMessage(R.string.uploaded);
                        })
                        .addOnFailureListener(e -> {
                            getViewState().hideProgressDialog();
                            getViewState().showMessage(R.string.error_uploading);
                        })
                        .addOnProgressListener(taskSnapshot -> {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            getViewState().setProgressDialog("Uploaded " + (int) progress + "%");
                        });
            }
        }
    }

    private String initDate() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}