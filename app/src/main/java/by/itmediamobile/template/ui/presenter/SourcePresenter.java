package by.itmediamobile.template.ui.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import by.itmediamobile.template.app.App;
import by.itmediamobile.template.model.Source;
import by.itmediamobile.template.model.SourceCategory;
import by.itmediamobile.template.repository.SourceRepository;
import by.itmediamobile.template.ui.view.SourceView;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Denis Kholevinsky
 */

public class SourcePresenter extends MvpBasePresenter<SourceView> implements Observer<List<Source>> {

    @Inject
    SourceRepository sourceRepository;

    private Subscription subscription;

    public SourcePresenter() {
        App.getDataComponent().inject(this);
    }

    public void getData(String category) {

        if (isViewAttached()) {
            subscription = sourceRepository.getSourceList(category)
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
    public void onNext(List<Source> sources) {
        if (isViewAttached()) {
            getView().setData(sources);
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
