package by.itmediamobile.template.ui.view;

import android.support.v4.app.Fragment;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Denis Kholevinsky
 */

public interface MainActivityView extends MvpView {

    void showChildFragment(Fragment fragment);

}
