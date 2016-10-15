package by.itmediamobile.template.ui.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import by.itmediamobile.template.ui.fragment.CategoryPageFragment;
import by.itmediamobile.template.ui.view.MainActivityView;

/**
 * Created by Denis Kholevinsky
 */

@SuppressWarnings("ALL")
public class MainActivityPresenter extends MvpBasePresenter<MainActivityView> {

    public void choiceFragment() {
        if (isViewAttached()) {
            getView().showChildFragment(new CategoryPageFragment());
        }
    }

}
