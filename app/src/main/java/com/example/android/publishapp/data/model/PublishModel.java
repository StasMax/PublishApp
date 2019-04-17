package com.example.android.publishapp.data.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PublishModel {
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("category")
    @Expose
    private List<String> category;
    @SerializedName("tag")
    @Expose
    private List<String> tag;
    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageFile")
    @Expose
    private List<String> imageFile;
    @SerializedName("link")
    @Expose
    private List<String> link;
    @SerializedName("linkName")
    @Expose
    private List<String> linkName;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("typeViewHolder")
    @Expose
    private int typeViewHolder;

    public PublishModel(int typeViewHolder) {
        this.typeViewHolder = typeViewHolder;
    }

    public PublishModel() {
    }

    public PublishModel(long id, List<String> category, List<String> tag, String header, String description, List<String> imageFile, List<String> link, List<String> linkName, String date, int typeViewHolder) {
        this.category = category;
        this.tag = tag;
        this.header = header;
        this.description = description;
        this.imageFile = imageFile;
        this.link = link;
        this.linkName = linkName;
        this.date = date;
        this.typeViewHolder = typeViewHolder;
        this.id = id;
    }

    public PublishModel(long id, List<String> category, List<String> tag, String header, String description, List<String> imageFile, List<String> link, List<String> linkName, int typeViewHolder) {
        this.category = category;
        this.tag = tag;
        this.header = header;
        this.description = description;
        this.imageFile = imageFile;
        this.link = link;
        this.linkName = linkName;
        this.typeViewHolder = typeViewHolder;
        this.id = id;
    }

    public PublishModel(long id, List<String> category, List<String> tag, List<String> link, List<String> linkName, int typeViewHolder) {
        this.category = category;
        this.tag = tag;
        this.link = link;
        this.linkName = linkName;
        this.typeViewHolder = typeViewHolder;
        this.id = id;
    }
}