package com.example.android.publishapp.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.PostView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.publishapp.presentation.Constant.TYPE_POST;

@InjectViewState
public class PostPresenter extends BasePresenter<PostView> {
    private IPublishIteractor publishIteractor;

    @Inject
    public PostPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public void initSendPost() {

        if (getCategories() == null || getTags() == null || getLinks().size() != getLinksNames().size()) {
            getViewState().showMesage(R.string.error_fields);
        } else {
            PublishModel publishModel = new PublishModel(getCategories(), getTags(), getHeader(), getDescription(), getFileImage(), getLinks(), getLinksNames(), TYPE_POST);

            disposeBag(publishIteractor.insertPostInDb(publishModel)

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<PublishModel>() {
                        @Override
                        public void accept(PublishModel publishModel) throws Exception {
                           // clearObjects();
                        }
                    }));
            getViewState().showMesage(R.string.success_post);

        }
    }
}
