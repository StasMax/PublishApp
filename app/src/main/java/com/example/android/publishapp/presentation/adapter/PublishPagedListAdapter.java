package com.example.android.publishapp.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.presentation.adapter.viewHolders.EventViewHolder;
import com.example.android.publishapp.presentation.adapter.viewHolders.LinkViewHolder;
import com.example.android.publishapp.presentation.adapter.viewHolders.PostViewHolder;

import static android.support.v7.widget.RecyclerView.*;
import static com.example.android.publishapp.presentation.Constant.TYPE_EVENT;
import static com.example.android.publishapp.presentation.Constant.TYPE_LINK;
import static com.example.android.publishapp.presentation.Constant.TYPE_POST;

public class PublishPagedListAdapter extends android.arch.paging.PagedListAdapter<PublishModel, ViewHolder> {


    public PublishPagedListAdapter(@NonNull DiffUtil.ItemCallback<PublishModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case TYPE_POST:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_post, parent, false);
                return new PostViewHolder(view);
            case TYPE_EVENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_event, parent, false);
                return new EventViewHolder(view);
            case TYPE_LINK:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_link, parent, false);
                return new LinkViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if (viewHolder instanceof PostViewHolder) {
            ((PostViewHolder) viewHolder).bind(getItem(i));
        } else if (viewHolder instanceof EventViewHolder) {
            ((EventViewHolder) viewHolder).bind(getItem(i));
        } else if (viewHolder instanceof LinkViewHolder) {
            ((LinkViewHolder) viewHolder).bind(getItem(i));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) != null) {
            PublishModel object = getItem(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }
}