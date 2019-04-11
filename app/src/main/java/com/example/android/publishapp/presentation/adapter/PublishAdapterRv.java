package com.example.android.publishapp.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.presentation.adapter.viewHolders.EventViewHolder;
import com.example.android.publishapp.presentation.adapter.viewHolders.LinkViewHolder;
import com.example.android.publishapp.presentation.adapter.viewHolders.PostViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.publishapp.presentation.Constant.TYPE_LINK;
import static com.example.android.publishapp.presentation.Constant.TYPE_EVENT;
import static com.example.android.publishapp.presentation.Constant.TYPE_POST;

public class PublishAdapterRv extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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