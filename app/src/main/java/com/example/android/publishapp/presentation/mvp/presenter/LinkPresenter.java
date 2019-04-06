package com.example.android.publishapp.presentation.mvp.presenter;

import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.LinkView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.publishapp.presentation.Constant.TYPE_LINK;
import static com.example.android.publishapp.presentation.Constant.TYPE_POST;

public class LinkPresenter extends BasePresenter<LinkView> {
    private IPublishIteractor publishIteractor;

    @Inject
    public LinkPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }
    public void initSendLink() {
        if (getCategories() == null || getTags() == null || getLinks().size() != getLinksNames().size()) {
            getViewState().showMesage(R.string.error_fields);
        } else {
            PublishModel publishModel = new PublishModel(getCategories(), getTags(), getLinks(), getLinksNames(), TYPE_LINK);
          /*  disposeBag(publishIteractor.insertPostInCloud(publishModel)
                    .doFinally(this::clearObjects)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());*/
        }
    }
}
