package com.example.android.publishapp.presentation.mvp.presenter;

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
                    .category(getCategories())
                    .tag(getTags())
                    .header(getHeader())
                    .description(getDescription())
                    .filePicture(getFileImage())
                    .link(getLinks())
                    .linkName(getLinksNames())
                    .type(TYPE_POST)
                    .build();

            disposeBag(publishIteractor.insertPostInDb(publishModel)
                    .doOnSuccess(publishModel1 -> getViewState().showMesage(R.string.success_post))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }
    }
}
