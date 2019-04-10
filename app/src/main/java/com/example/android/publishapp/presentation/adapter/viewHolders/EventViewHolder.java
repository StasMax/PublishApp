package com.example.android.publishapp.presentation.adapter.viewHolders;

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

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class EventViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.category_event)
    TextView eventCategory;
    @BindView(R.id.tag_event)
    TextView eventTag;
    @BindView(R.id.header_event)
    TextView eventHeader;
    @BindView(R.id.description_event)
    TextView eventDescription;
    @BindView(R.id.view_flipper_event)
    ViewFlipper viewFlipper;
    @BindView(R.id.link_event)
    TextView eventLink;
    @BindView(R.id.link_event_word)
    TextView eventLinkWord;
    @BindView(R.id.date_event)
    TextView eventDate;
    @BindView(R.id.date_event_word)
    TextView eventDateWord;
    @BindView(R.id.load_txt_event)
    TextView textLoad;
    private StringBuilder categoryBuilder = new StringBuilder();
    private StringBuilder tagBuilder = new StringBuilder();
    private StringBuilder linkBuilder = new StringBuilder();

    public EventViewHolder(@NonNull View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void bind(PublishModel publishModel) {
        if (publishModel == null) {
            textLoad.setText(R.string.load_txt);
        } else {
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

            if (publishModel.getLink() != null || publishModel.getLinkName() != null) {
                for (int i = 0; i < publishModel.getLink().size(); i++) {
                    linkBuilder.append(" " + "<a href=").append(publishModel.getLink().get(i)).append("><font color=#AAA>").append(publishModel.getLinkName().get(i)).append("</font></a>").append(",");
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
                eventDescription.setText(Html.fromHtml(publishModel.getDescription()));
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
    }

    private void setImageInFlipper(String imageUrl) {
        ImageView image = new ImageView(itemView.getContext());
        Picasso.with(itemView.getContext()).load(imageUrl).into(image);
        viewFlipper.addView(image);
    }
}
