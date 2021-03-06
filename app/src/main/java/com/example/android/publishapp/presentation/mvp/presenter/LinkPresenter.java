package com.example.android.publishapp.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.LinkView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.publishapp.presentation.Constant.TYPE_LINK;

@InjectViewState
public class LinkPresenter extends CommonFieldsPresenter<LinkView> {
    private IPublishIteractor publishIteractor;

    @Inject
    public LinkPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public void initSendLink() {
        if (categories == null || tags == null || links.size() != linksNames.size()) {
            getViewState().showMessage(R.string.error_fields);
        } else {
            PublishModel publishModel = PublishModel.builder()
                    .id(lastId)
                    .category(categories)
                    .tag(tags)
                    .link(links)
                    .linkName(linksNames)
                    .typeViewHolder(TYPE_LINK)
                    .build();

            disposeBag(publishIteractor.insertPostInDb(publishModel)
                    .doOnSuccess(publishModel1 -> getViewState().showMessage(R.string.success_post))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }
    }
}