package com.example.android.publishapp.presentation.mvp.presenter;

import com.arellomobile.mvp.MvpView;
import com.example.android.publishapp.presentation.mvp.view.PublishView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class CommonFieldsPresenter <View extends MvpView> extends BasePresenter<PublishView> {
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
}
