package com.example.android.publishapp.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;

import java.util.Map;

public class LinkViewHolder extends RecyclerView.ViewHolder {
    private TextView linkLink;
    private StringBuilder linkBuilder;

    public LinkViewHolder(View v) {
        super(v);
        linkLink = v.findViewById(R.id.link_link);
        linkBuilder = new StringBuilder();
    }

    public void bind(PublishModel publishModel) {
        if (publishModel.getLink() != null) {
            for (Map.Entry entry : publishModel.getLink().entrySet()) {
                linkBuilder.append(" " + "<a href=").append(entry.getKey()).append("><font color=#AAA>").append(entry.getValue()).append("</font></a>").append(",");
            }
            linkBuilder.deleteCharAt(linkBuilder.length() - 1);
            linkLink.setText(Html.fromHtml(linkBuilder.toString()));
            linkLink.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
