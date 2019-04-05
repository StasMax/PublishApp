package com.example.android.publishapp.presentation.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android.publishapp.R;
import com.example.android.publishapp.presentation.app.App;
import com.example.android.publishapp.presentation.mvp.presenter.PublishPresenter;
import com.example.android.publishapp.presentation.mvp.view.PublishView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends MvpAppCompatFragment implements PublishView {

    public static final int PICK_IMAGE = 1;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Inject
    @InjectPresenter
    PublishPresenter publishPresenter;

    @ProvidePresenter
    PublishPresenter providePresenter() {
        return publishPresenter;
    }
    private Unbinder unbinder;

    public PostFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.getComponent().inject(this);
        FirebaseApp.initializeApp(getContext());
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        unbinder = ButterKnife.bind(this, view);
        FirebaseApp app = FirebaseApp.getInstance();
        storage = FirebaseStorage.getInstance(app);
        storageReference = storage.getReference("images");
        return view;
    }

    @OnTextChanged(R.id.edit_category_post)
    public void onCategoryTextChanged(CharSequence s, int start, int before, int count) {
        publishPresenter.fieldCategory(s.toString());
    }

    @OnTextChanged(R.id.edit_tag_post)
    public void onTagTextChanged(CharSequence s, int start, int before, int count) {
        publishPresenter.fieldTag(s.toString());
    }

    @OnTextChanged(R.id.edit_header_post)
    public void onHeaderTextChanged(CharSequence s, int start, int before, int count) {
        publishPresenter.fieldHeader(s.toString());
    }

    @OnTextChanged(R.id.edit_description_post)
    public void onDescriptionTextChanged(CharSequence s, int start, int before, int count) {
        publishPresenter.fieldDescription(s.toString());
    }

    @OnTextChanged(R.id.edit_link_post)
    public void onLinkTextChanged(CharSequence s, int start, int before, int count) {
        publishPresenter.fieldLink(s.toString());
    }

    @OnTextChanged(R.id.edit_link_post_name)
    public void onLinkNameTextChanged(CharSequence s, int start, int before, int count) {
        publishPresenter.fieldLinkName(s.toString());
    }

    @Override
    public void showError(int resource) {
        Toast.makeText(getContext(), resource, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button_send_post)
    void onClickPost() {
        publishPresenter.initSendPost();
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
        publishPresenter.initUploadImage(requestCode, resultCode, data, storageReference, getContext());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
