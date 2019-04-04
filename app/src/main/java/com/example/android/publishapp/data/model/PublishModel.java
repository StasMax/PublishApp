package com.example.android.publishapp.data.model;

import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.Data;

@Data
public class PublishModel {
    @SerializedName("category")
    @Expose
    private String [] category;
    @SerializedName("tag")
    @Expose
    private String [] tag;
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
    private Map<String, String> link;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("typeViewHolder")
    @Expose
    private int type;

    public PublishModel(String[] category, String[] tag, String header, String description, List<String> filePicture, Map<String, String> link, String date, int type) {
        this.category = category;
        this.tag = tag;
        this.header = header;
        this.description = description;
        this.filePicture = filePicture;
        this.link = link;
        this.date = date;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishModel that = (PublishModel) o;
        return Arrays.equals(category, that.category) &&
                Objects.equals(header, that.header) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(header, description);
        result = 31 * result + Arrays.hashCode(category);
        return result;
    }
}
