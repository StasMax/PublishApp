package com.example.android.publishapp.presentation.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.PublishView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

@InjectViewState
public class PublishPresenter extends BasePresenter<PublishView> {
    private IPublishIteractor publishIteractor;
    private String[] categories = null;
    private String[] tags = null;
    private String header = null;
    private String description = null;
    private List<String> filePicture = null;
    private Map<String, String> links = null;
    private final String date = null;
    private final int type = 0;
private Map<String, String> linksListener = new HashMap<>();
    @Inject
    public PublishPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public void fieldCategory(String category) {
        categories = category.split(", ");
        Log.e("catEG", category);
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
linksListener.
    }

    public void fieldLinkName(String linkName) {

    }
}
