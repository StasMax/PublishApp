package com.example.android.publishapp.presentation.mvp.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android.publishapp.R;
import com.example.android.publishapp.presentation.app.App;
import com.example.android.publishapp.presentation.mvp.presenter.LinkPresenter;
import com.example.android.publishapp.presentation.mvp.view.PublishView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinkFragment extends MvpAppCompatFragment implements PublishView {
    FirebaseStorage storage;
    StorageReference storageReference;

    @Inject
    @InjectPresenter
    LinkPresenter linkPresenter;

    @ProvidePresenter
    LinkPresenter providePresenter() {
        return linkPresenter;
    }

    private Unbinder unbinder;

    public LinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.getComponent().inject(this);
        FirebaseApp.initializeApp(getContext());
        View view = inflater.inflate(R.layout.fragment_link, container, false);
        unbinder = ButterKnife.bind(this, view);
        FirebaseApp app = FirebaseApp.getInstance();
        storage = FirebaseStorage.getInstance(app);
        storageReference = storage.getReference("images");
        return view;
    }
    @OnTextChanged(R.id.edit_category_link)
    public void onCategoryTextChanged(CharSequence s, int start, int before, int count) {
        linkPresenter.fieldCategory(s.toString());
    }

    @OnTextChanged(R.id.edit_tag_link)
    public void onTagTextChanged(CharSequence s, int start, int before, int count) {
        linkPresenter.fieldTag(s.toString());
    }

    @OnTextChanged(R.id.edit_link_link)
    public void onLinkTextChanged(CharSequence s, int start, int before, int count) {
        linkPresenter.fieldLink(s.toString());
    }

    @OnTextChanged(R.id.edit_link_link_name)
    public void onLinkNameTextChanged(CharSequence s, int start, int before, int count) {
        linkPresenter.fieldLinkName(s.toString());
    }

    @Override
    public void showMesage(int resource) {
        Toast.makeText(getContext(), resource, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button_send_link)
    void onClickPost() {
        linkPresenter.initSendLink();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
