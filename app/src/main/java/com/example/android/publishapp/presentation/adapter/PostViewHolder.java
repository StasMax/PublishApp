package com.example.android.publishapp.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class PostViewHolder extends RecyclerView.ViewHolder {
    private TextView postCategory;
    private TextView postTag;
    private TextView postHeader;
    private TextView postDescription;
    private ImageView postFilePicture;
    private TextView postLink;
    private TextView postLinkWord;
    private StringBuilder categoryBuilder;
    private StringBuilder tagBuilder;
    private StringBuilder linkBuilder;

    public PostViewHolder(View itemView) {
        super(itemView);
        postCategory = itemView.findViewById(R.id.category_post);
        postTag = itemView.findViewById(R.id.tag_post);
        postHeader = itemView.findViewById(R.id.header_post);
        postDescription = itemView.findViewById(R.id.description_post);
        postFilePicture = itemView.findViewById(R.id.file_picture_post);
        postLink = itemView.findViewById(R.id.link_post);
        postLinkWord = itemView.findViewById(R.id.link_post_word);
        categoryBuilder = new StringBuilder();
        tagBuilder = new StringBuilder();
        linkBuilder = new StringBuilder();
    }

    public void bind(PublishModel publishModel) {
        for (String category : publishModel.getCategory()) {
            categoryBuilder.append(" ").append(category).append(",");
        }
        categoryBuilder.deleteCharAt(categoryBuilder.length() - 1);
        postCategory.setText(categoryBuilder.toString());

        for (String tag : publishModel.getTag()) {
            tagBuilder.append(" ").append(tag).append(",");
        }
        tagBuilder.deleteCharAt(tagBuilder.length() - 1);
        postTag.setText(tagBuilder.toString());

        if (publishModel.getLink() != null) {
            for (int i = 0; i < publishModel.getLink().size(); i++) {
                linkBuilder.append(" " + "<a href=").append(publishModel.getLink().get(i)).append("><font color=#AAA>").append(publishModel.getLinkName().get(i)).append("</font></a>").append(",");
            }
            linkBuilder.deleteCharAt(linkBuilder.length() - 1);
            postLink.setText(Html.fromHtml(linkBuilder.toString()));
            postLink.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            postLink.setVisibility(View.GONE);
            postLinkWord.setVisibility(View.GONE);
        }

        if (publishModel.getHeader() != null) {
            postHeader.setText(publishModel.getHeader());
        } else {
            postHeader.setVisibility(View.GONE);
        }

        if (publishModel.getDescription() != null) {
            postDescription.setText(publishModel.getDescription());
        } else {
            postDescription.setVisibility(View.GONE);
        }

        if (publishModel.getFilePicture() != null) {
            Picasso.with(itemView.getContext()).load(publishModel.getFilePicture().get(0)).into(postFilePicture);
        }else {
            postFilePicture.setVisibility(View.GONE);
        }
    }
}
