package com.example.android.publishapp.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.squareup.picasso.Picasso;

import java.util.Map;

import static android.view.View.GONE;

public class EventViewHolder extends RecyclerView.ViewHolder {
    private TextView eventCategory;
    private TextView eventTag;
    private TextView eventHeader;
    private TextView eventDescription;
    private ViewFlipper viewFlipper;
    private TextView eventLink;
    private TextView eventLinkWord;
    private TextView eventDate;
    private TextView eventDateWord;
    private StringBuilder categoryBuilder;
    private StringBuilder tagBuilder;
    private StringBuilder linkBuilder;

    public EventViewHolder(@NonNull View v) {
        super(v);
        eventCategory = v.findViewById(R.id.category_event);
        eventTag = v.findViewById(R.id.tag_event);
        eventHeader = v.findViewById(R.id.header_event);
        eventDescription = v.findViewById(R.id.description_event);
        viewFlipper = v.findViewById(R.id.view_flipper_event);
        eventLink = v.findViewById(R.id.link_event);
        eventLinkWord = v.findViewById(R.id.link_post_word);
        eventDate = v.findViewById(R.id.date_event);
        eventDateWord = v.findViewById(R.id.date_event_word);
        categoryBuilder = new StringBuilder();
        tagBuilder = new StringBuilder();
        linkBuilder = new StringBuilder();
    }

    public void bind(PublishModel publishModel) {
        for (String category : publishModel.getCategory()) {
            categoryBuilder.append(" ").append(category).append(",");
        }
        categoryBuilder.deleteCharAt(categoryBuilder.length() - 1);
        eventCategory.setText(categoryBuilder.toString());

        for (String tag : publishModel.getTag()) {
            tagBuilder.append(" ").append(tag).append(",");
        }
        tagBuilder.deleteCharAt(tagBuilder.length() - 1);
        eventTag.setText(tagBuilder.toString());

        if (publishModel.getLink() != null) {
            for (Map.Entry entry : publishModel.getLink().entrySet()) {
                linkBuilder.append(" " + "<a href=").append(entry.getKey()).append("><font color=#AAA>").append(entry.getValue()).append("</font></a>").append(",");
            }
            linkBuilder.deleteCharAt(linkBuilder.length() - 1);
            eventLink.setText(Html.fromHtml(linkBuilder.toString()));
            eventLink.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            eventLink.setVisibility(GONE);
            eventLinkWord.setVisibility(GONE);
        }

        if (publishModel.getHeader() != null) {
            eventHeader.setText(publishModel.getHeader());
        } else {
            eventHeader.setVisibility(GONE);
        }

        if (publishModel.getDescription() != null) {
            eventDescription.setText(publishModel.getDescription());
        } else {
            eventDescription.setVisibility(GONE);
        }

        if (publishModel.getFilePicture() != null) {
            for (String imageUrl : publishModel.getFilePicture()) {
                setImageInFlipper(imageUrl);
            }
        } else {
            viewFlipper.setVisibility(GONE);
        }

        if (publishModel.getDate() != null) {
            eventDate.setText(publishModel.getDate());
        } else {
            eventDate.setVisibility(GONE);
            eventDateWord.setVisibility(GONE);
        }
    }

    private void setImageInFlipper(String imageUrl) {
        ImageView image = new ImageView(itemView.getContext());
        Picasso.with(itemView.getContext()).load(imageUrl).into(image);
        viewFlipper.addView(image);
    }
}
