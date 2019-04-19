package com.example.android.publishapp.presentation.mvp.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android.publishapp.R;
import com.example.android.publishapp.presentation.app.App;
import com.example.android.publishapp.presentation.mvp.presenter.EventPresenter;
import com.example.android.publishapp.presentation.mvp.view.PublishView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

import static com.example.android.publishapp.presentation.Constant.PICK_IMAGE;

public class EventFragment extends BaseFragmentActivity implements PublishView {
    private ProgressDialog progressDialog;
    @Inject
    @InjectPresenter
    EventPresenter eventPresenter;

    @ProvidePresenter
    EventPresenter providePresenter() {
        return eventPresenter;
    }

    private Unbinder unbinder;

    public EventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.getComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnTextChanged(R.id.edit_category_event)
    public void onCategoryTextChanged(CharSequence s, int start, int before, int count) {
        eventPresenter.fieldCategory(s.toString());
    }

    @OnTextChanged(R.id.edit_tag_event)
    public void onTagTextChanged(CharSequence s, int start, int before, int count) {
        eventPresenter.fieldTag(s.toString());
    }

    @OnTextChanged(R.id.edit_header_event)
    public void onHeaderTextChanged(CharSequence s, int start, int before, int count) {
        eventPresenter.fieldHeader(s.toString());
    }

    @OnTextChanged(R.id.edit_description_event)
    public void onDescriptionTextChanged(CharSequence s, int start, int before, int count) {
        eventPresenter.fieldDescription(s.toString());
    }

    @OnTextChanged(R.id.edit_link_event)
    public void onLinkTextChanged(CharSequence s, int start, int before, int count) {
        eventPresenter.fieldLink(s.toString());
    }

    @OnTextChanged(R.id.edit_link_event_name)
    public void onLinkNameTextChanged(CharSequence s, int start, int before, int count) {
        eventPresenter.fieldLinkName(s.toString());
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

    @OnClick(R.id.button_send_event)
    void onClickPost() {
        eventPresenter.initSendEvent();
    }

    @OnClick(R.id.button_image_event)
    void onClickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        eventPresenter.initUploadImage(requestCode, resultCode, data);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}