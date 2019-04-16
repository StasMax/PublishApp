package com.example.android.publishapp.presentation.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.PublishView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.publishapp.presentation.Constant.TYPE_POST;

@InjectViewState
public class PostPresenter extends CommonFieldsPresenter<PublishView> {
    private IPublishIteractor publishIteractor;

    @Inject
    public PostPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public void initSendPost() {

        if (getCategories() == null || getTags() == null || getLinks().size() != getLinksNames().size()) {
            getViewState().showMesage(R.string.error_fields);
        } else {
            PublishModel publishModel = PublishModel.builder()
                    .id(getLastId())
                    .category(getCategories())
                    .tag(getTags())
                    .header(getHeader())
                    .description(getDescription())
                    .imageFile(getFileImage())
                    .link(getLinks())
                    .linkName(getLinksNames())
                    .typeViewHolder(TYPE_POST)
                    .build();

            disposeBag(publishIteractor.insertPostInDb(publishModel)
                    .doAfterSuccess(publishModel12 -> getFileImage().clear())
                    .doOnSuccess(publishModel1 -> getViewState().showMesage(R.string.success_post))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }
    }
}
