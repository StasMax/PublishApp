package com.example.android.publishapp.presentation.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android.publishapp.R;
import com.example.android.publishapp.presentation.app.App;
import com.example.android.publishapp.presentation.mvp.presenter.PostPresenter;
import com.example.android.publishapp.presentation.mvp.view.PublishView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

import static com.example.android.publishapp.presentation.Constant.PICK_IMAGE;

public class PostFragment extends MvpAppCompatFragment implements PublishView {

    private Unbinder unbinder;
    @Inject
    @InjectPresenter
    PostPresenter postPresenter;

    @ProvidePresenter
    PostPresenter providePresenter() {
        return postPresenter;
    }

    public PostFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.getComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnTextChanged(R.id.edit_category_post)
    public void onCategoryTextChanged(CharSequence s, int start, int before, int count) {
        postPresenter.fieldCategory(s.toString());
    }

    @OnTextChanged(R.id.edit_tag_post)
    public void onTagTextChanged(CharSequence s, int start, int before, int count) {
        postPresenter.fieldTag(s.toString());
    }

    @OnTextChanged(R.id.edit_header_post)
    public void onHeaderTextChanged(CharSequence s, int start, int before, int count) {
        postPresenter.fieldHeader(s.toString());
    }

    @OnTextChanged(R.id.edit_description_post)
    public void onDescriptionTextChanged(CharSequence s, int start, int before, int count) {
        postPresenter.fieldDescription(s.toString());
    }

    @OnTextChanged(R.id.edit_link_post)
    public void onLinkTextChanged(CharSequence s, int start, int before, int count) {
        postPresenter.fieldLink(s.toString());
    }

    @OnTextChanged(R.id.edit_link_post_name)
    public void onLinkNameTextChanged(CharSequence s, int start, int before, int count) {
        postPresenter.fieldLinkName(s.toString());
    }

    @Override
    public void showMesage(int resource) {
        Toast.makeText(getContext(), resource, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button_send_post)
    void onClickPost() {
        postPresenter.initSendPost();
    }

    @OnClick(R.id.button_image)
    void onClickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        postPresenter.initUploadImage(requestCode, resultCode, data, getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}