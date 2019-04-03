package com.example.android.publishapp.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;

import java.util.ArrayList;
import java.util.List;

public class PublishAdapterRv extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_POST = 0;
    private final int TYPE_EVENT = 1;
    private final int TYPE_LINK = 2;
    private List<PublishModel> publishModelList = new ArrayList<>();

    public void setupPublishers(List<PublishModel> publishModels) {
        publishModelList.clear();
        publishModelList.addAll(publishModels);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        switch (i) {
            case TYPE_POST:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_post, viewGroup, false);
                return new PostViewHolder(view);
            case TYPE_EVENT:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_event, viewGroup, false);
                return new EventViewHolder(view);
            case TYPE_LINK:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_link, viewGroup, false);
                return new LinkViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (publishModelList.get(i) != null) {
            switch (publishModelList.get(i).getType()) {
                case TYPE_POST:
                    ((PostViewHolder) viewHolder).bind(publishModelList.get(i));
                    break;
                case TYPE_EVENT:
                    ((EventViewHolder) viewHolder).bind(publishModelList.get(i));
                    break;
                case TYPE_LINK:
                    ((LinkViewHolder) viewHolder).bind(publishModelList.get(i));
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return publishModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (publishModelList != null) {
            PublishModel object = publishModelList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }
}
