package com.example.android.publishapp.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.example.android.publishapp.data.model.PublishModel;

public class PublishDiffUtilCallback  {

    public DiffUtil.ItemCallback<PublishModel>diffUtilCallback = new DiffUtil.ItemCallback<PublishModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull PublishModel publishModel, @NonNull PublishModel t1) {
            return publishModel.getCategory() == t1.getCategory();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PublishModel publishModel, @NonNull PublishModel t1) {
            return publishModel.getDescription().equals(t1.getDescription())
                    && publishModel.getType() == t1.getType();
        }
    };
}
