package com.example.android.publishapp.data.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    private List<String> filePicture;
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
    private int type;

    public PublishModel(int type) {
        this.type = type;
    }

    public PublishModel() {
    }

    public PublishModel(long id, List<String> category, List<String> tag, String header, String description, List<String> filePicture, List<String> link, List<String> linkName, String date, int type) {
        this.category = category;
        this.tag = tag;
        this.header = header;
        this.description = description;
        this.filePicture = filePicture;
        this.link = link;
        this.linkName = linkName;
        this.date = date;
        this.type = type;
        this.id = id;
    }

    public PublishModel(long id, List<String> category, List<String> tag, String header, String description, List<String> filePicture, List<String> link, List<String> linkName, int type) {
        this.category = category;
        this.tag = tag;
        this.header = header;
        this.description = description;
        this.filePicture = filePicture;
        this.link = link;
        this.linkName = linkName;
        this.type = type;
        this.id = id;
    }

    public PublishModel(long id, List<String> category, List<String> tag, List<String> link, List<String> linkName, int type) {
        this.category = category;
        this.tag = tag;
        this.link = link;
        this.linkName = linkName;
        this.type = type;
        this.id = id;
    }

}
