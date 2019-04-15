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
import com.example.android.publishapp.presentation.adapter.viewHolders.LoadingVH;
import com.example.android.publishapp.presentation.adapter.viewHolders.PostViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.publishapp.presentation.Constant.TYPE_LOADING;
import static com.example.android.publishapp.presentation.Constant.TYPE_LINK;
import static com.example.android.publishapp.presentation.Constant.TYPE_EVENT;
import static com.example.android.publishapp.presentation.Constant.TYPE_POST;

public class PublishAdapterRv extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PublishModel> publishModelList = new ArrayList<>();
    private boolean isLoadingAdded = false;

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
            case TYPE_LOADING:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_progress, viewGroup, false);
                return new LoadingVH(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (publishModelList.get(i) != null) {
            switch (publishModelList.get(i).getTypeViewHolder()) {
                case TYPE_POST:
                    ((PostViewHolder) viewHolder).bind(publishModelList.get(i));
                    break;
                case TYPE_EVENT:
                    ((EventViewHolder) viewHolder).bind(publishModelList.get(i));
                    break;
                case TYPE_LINK:
                    ((LinkViewHolder) viewHolder).bind(publishModelList.get(i));
                    break;
                case TYPE_LOADING:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return publishModelList == null ? 0 : publishModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (publishModelList != null) {
            if (position == publishModelList.size() - 1 && isLoadingAdded) {
                return TYPE_LOADING;
            } else {
                PublishModel object = publishModelList.get(position);
                if (object != null) {
                    return object.getTypeViewHolder();
                }
            }
        }
        return 0;
    }

    public void add(PublishModel r) {
        publishModelList.add(r);
        notifyItemInserted(publishModelList.size() - 1);
    }

    public void addAll(List<PublishModel> moveResults) {
        for (PublishModel result : moveResults) {
            add(result);
        }
    }

    public void remove(PublishModel r) {
        int position = publishModelList.indexOf(r);
        if (position > -1) {
            publishModelList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new PublishModel(TYPE_LOADING));
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = publishModelList.size() - 1;
        PublishModel result = getItem(position);

        if (result != null) {
            publishModelList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public PublishModel getItem(int position) {
        return publishModelList.get(position);
    }

}
