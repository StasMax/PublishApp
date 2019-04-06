package com.example.android.publishapp.presentation.mvp.presenter;

import com.example.android.publishapp.R;
import com.example.android.publishapp.data.model.PublishModel;
import com.example.android.publishapp.domain.iteractor.IPublishIteractor;
import com.example.android.publishapp.presentation.mvp.view.EventView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.publishapp.presentation.Constant.TYPE_EVENT;

public class EventPresenter extends BasePresenter<EventView> {
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
            disposeBag(publishIteractor.insertPostInCloud(publishModel)
                    .doFinally(this::clearObjects)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }
    }

    private String initDate() {
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
        return dateformat.format(Calendar.getInstance().getTime());
    }
}
