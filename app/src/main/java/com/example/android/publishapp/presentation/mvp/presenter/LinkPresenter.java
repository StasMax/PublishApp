package com.example.android.publishapp.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.PublishView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.publishapp.presentation.Constant.TYPE_LINK;

@InjectViewState
public class LinkPresenter extends CommonFieldsPresenter<PublishView> {
    private IPublishIteractor publishIteractor;

    @Inject
    public LinkPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public void initSendLink() {
        if (getCategories() == null || getTags() == null || getLinks().size() != getLinksNames().size()) {
            getViewState().showMesage(R.string.error_fields);
        } else {
            PublishModel publishModel = PublishModel.builder()
                    .id(getFieldId())
                    .category(getCategories())
                    .tag(getTags())
                    .link(getLinks())
                    .linkName(getLinksNames())
                    .typeViewHolder(TYPE_LINK)
                    .build();

           disposeBag(publishIteractor.insertPostInDb(publishModel)
                    .doOnSuccess(publishModel1 -> getViewState().showMesage(R.string.success_post))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }
    }
}
