package com.example.android.publishapp.presentation.mvp.ui.fragment;

import android.app.ProgressDialog;
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

public class PostFragment extends BaseFragmentActivity implements PublishView {
    private ProgressDialog progressDialog;
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

    @OnTextChanged({R.id.edit_category_post, R.id.edit_header_post, R.id.edit_tag_post,
            R.id.edit_description_post, R.id.edit_link_post, R.id.edit_link_post_name})
    public void onFieldsTextChanged(CharSequence s, int start, int before, int count) {
        if (getActivity() == null || getActivity().getCurrentFocus() == null) {
            return;
        }

        String text = s.toString();
        switch (getActivity().getCurrentFocus().getId()) {
            case R.id.edit_category_post:
                postPresenter.fieldCategory(text);
                break;
            case R.id.edit_tag_post:
                postPresenter.fieldTag(text);
                break;
            case R.id.edit_header_post:
                postPresenter.fieldHeader(text);
                break;
            case R.id.edit_description_post:
                postPresenter.fieldDescription(text);
                break;
            case R.id.edit_link_post:
                postPresenter.fieldLink(text);
                break;
            case R.id.edit_link_post_name:
                postPresenter.fieldLinkName(text);
                break;
        }
    }

    @Override
    public void showMessage(int resource) {
        baseShowMessage(resource);
    }

    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Загрузка...");
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void setProgressDialog(String text) {
        progressDialog.setMessage(text);
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
        postPresenter.initUploadImage(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}