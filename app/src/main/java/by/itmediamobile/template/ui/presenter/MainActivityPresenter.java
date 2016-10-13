package by.itmediamobile.template.ui.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import by.itmediamobile.template.ui.fragment.FeedFragment;
import by.itmediamobile.template.ui.view.MainActivityView;

/**
 * Created by Denis Kholevinsky
 */

public class MainActivityPresenter extends MvpBasePresenter<MainActivityView> {

    public void choiceFragment() {
        getView().showChildFragment(new FeedFragment());
    }

}
