package com.example.android.publishapp.presentation.mvp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android.publishapp.R;
import com.example.android.publishapp.presentation.app.App;
import com.example.android.publishapp.presentation.mvp.presenter.PublishPresenter;
import com.example.android.publishapp.presentation.mvp.view.PublishView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class PublishActivity extends MvpAppCompatActivity implements PublishView {
    @BindView(R.id.edit_category_post)
    EditText editCategoryText;
    @BindView(R.id.edit_tag_post)
    EditText editTagText;
   // CompositeDisposable disposeBag = new CompositeDisposable();
    @Inject
    @InjectPresenter
    PublishPresenter publishPresenter;

    @ProvidePresenter
    PublishPresenter providePresenter() {
        return publishPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        //rxListeners();
    }
public void rxListeners() {
    publishPresenter.disposeBag(RxTextView.afterTextChangeEvents(editCategoryText)
            .skipInitialValue()
            .filter(textViewAfterTextChangeEvent -> (Objects.requireNonNull(textViewAfterTextChangeEvent.editable()).toString().length() > 1))
            .subscribe(textViewAfterTextChangeEvent -> publishPresenter.fieldCategory(textViewAfterTextChangeEvent.toString())));

    publishPresenter.disposeBag(RxTextView.afterTextChangeEvents(editTagText)
            .skipInitialValue()
            .filter(textViewAfterTextChangeEvent -> (Objects.requireNonNull(textViewAfterTextChangeEvent.editable()).toString().length() > 1))
            .subscribe(textViewAfterTextChangeEvent -> publishPresenter.fieldTag(textViewAfterTextChangeEvent.toString())));
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
        Toast.makeText(this, resource, Toast.LENGTH_SHORT).show();
    }
}