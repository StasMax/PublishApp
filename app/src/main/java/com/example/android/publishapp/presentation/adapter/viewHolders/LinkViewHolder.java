package com.example.android.publishapp.presentation.adapter.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;

import java.util.Map;

public class LinkViewHolder extends RecyclerView.ViewHolder {
    private TextView linkCategory;
    private TextView linkTag;
    private TextView linkLink;
    private TextView textLoad;
    private StringBuilder linkBuilder;
    private StringBuilder categoryBuilder;
    private StringBuilder tagBuilder;

    public LinkViewHolder(View v) {
        super(v);
        linkCategory = itemView.findViewById(R.id.category_link);
        linkTag = itemView.findViewById(R.id.tag_link);
        linkLink = v.findViewById(R.id.link_link);
        linkBuilder = new StringBuilder();
        categoryBuilder = new StringBuilder();
        tagBuilder = new StringBuilder();
        textLoad = itemView.findViewById(R.id.load_txt_link);
    }

    public void bind(PublishModel publishModel) {
        if (publishModel == null) {
            textLoad.setText(R.string.load_txt);
        } else {
            for (String category : publishModel.getCategory()) {
                categoryBuilder.append(" ").append(category).append(",");
            }
            categoryBuilder.deleteCharAt(categoryBuilder.length() - 1);
            linkCategory.setText(categoryBuilder.toString());

            for (String tag : publishModel.getTag()) {
                tagBuilder.append(" ").append(tag).append(",");
            }
            tagBuilder.deleteCharAt(tagBuilder.length() - 1);
            linkTag.setText(tagBuilder.toString());

            if (publishModel.getLink() != null) {
                for (int i = 0; i < publishModel.getLink().size(); i++) {
                    linkBuilder.append(" " + "<a href=").append(publishModel.getLink().get(i)).append("><font color=#AAA>").append(publishModel.getLinkName().get(i)).append("</font></a>").append(",");
                }
                linkBuilder.deleteCharAt(linkBuilder.length() - 1);
                linkLink.setText(Html.fromHtml(linkBuilder.toString()));
                linkLink.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }
    }
}
