package by.itmediamobile.template.ui.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import by.itmediamobile.template.app.App;
import by.itmediamobile.template.model.Feed;
import by.itmediamobile.template.repository.FeedRepository;
import by.itmediamobile.template.ui.view.FeedView;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Denis Kholevinsky
 */

public class FeedPresenter extends MvpBasePresenter<FeedView> implements Observer<List<Feed>>{

    @Inject
    FeedRepository feedRepository;

    private Subscription subscription;

    public FeedPresenter() {
        App.getDataComponent().inject(this);
    }

    public void getFeedData() {

        if (isViewAttached()) {
            getView().showLoading(false);

            subscription = feedRepository.getFeedList()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.computation())
                    .subscribe(this);
        }
    }

    @Override
    public void onCompleted() {
        if (isViewAttached()) {
            getView().showContent();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e, false);
        }
    }

    @Override
    public void onNext(List<Feed> feeds) {
        if (isViewAttached()) {
            getView().setData(feeds);
        }
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
