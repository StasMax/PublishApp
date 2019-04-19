package com.example.android.publishapp.presentation.mvp.ui.fragment;

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
import com.example.android.publishapp.presentation.mvp.presenter.LinkPresenter;
import com.example.android.publishapp.presentation.mvp.view.LinkView;
import com.example.android.publishapp.presentation.mvp.view.PublishView;
import com.google.firebase.FirebaseApp;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class LinkFragment extends BaseFragmentActivity implements LinkView {

    @Inject
    @InjectPresenter
    LinkPresenter linkPresenter;

    @ProvidePresenter
    LinkPresenter providePresenter() {
        return linkPresenter;
    }

    private Unbinder unbinder;

    public LinkFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.getComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_link, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnTextChanged({R.id.edit_category_link, R.id.edit_tag_link,
             R.id.edit_link_link, R.id.edit_link_link_name})
    public void onFieldsTextChanged(CharSequence s, int start, int before, int count) {
        if (getActivity() == null || getActivity().getCurrentFocus() == null) {
            return;
        }

        String text = s.toString();
        switch (getActivity().getCurrentFocus().getId()) {
            case R.id.edit_category_link:
                linkPresenter.fieldCategory(text);
                break;
            case R.id.edit_tag_post:
                linkPresenter.fieldTag(text);
                break;
            case R.id.edit_link_link:
                linkPresenter.fieldLink(text);
                break;
            case R.id.edit_link_link_name:
                linkPresenter.fieldLinkName(text);
                break;
        }
    }

    @Override
    public void showMessage(int resource) {
        baseShowMessage(resource);
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