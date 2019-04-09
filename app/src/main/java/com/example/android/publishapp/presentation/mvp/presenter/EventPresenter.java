package com.example.android.publishapp.presentation.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.PublishView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.publishapp.presentation.Constant.TYPE_EVENT;
@InjectViewState
public class EventPresenter extends BasePresenter<PublishView> {
    private IPublishIteractor publishIteractor;

    @Inject
    public EventPresenter(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public void initSendEvent() {
        if (getCategories() == null || getTags() == null || getLinks().size() != getLinksNames().size()) {
            getViewState().showMesage(R.string.error_fields);
        } else {
            PublishModel publishModel = new PublishModel(getCategories(), getTags(), getHeader(), getDescription(), getFileImage(), getLinks(), getLinksNames(), initDate(), TYPE_EVENT);
            disposeBag(publishIteractor.insertPostInDb(publishModel)
                    .doOnSuccess(publishModel1 -> getViewState().showMesage(R.string.success_post))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }
    }

    private String initDate() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
        return dateformat.format(Calendar.getInstance().getTime());
    }
}
