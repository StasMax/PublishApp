package com.example.android.publishapp.data.model;

import android.net.Uri;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.Data;

@Data
public class PublishModel {
    private String [] category;
    private String [] tag;
    private String header;
    private String description;
    private List<String> filePicture;
    private Map<String, String> link;
    private String date;
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
