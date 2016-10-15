package by.itmediamobile.template.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Denis Kholevinsky
 */

public abstract class BaseMvpViewStateFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends MvpLceViewStateFragment<CV, M, V, P> {

    protected Unbinder unbinder;

    @LayoutRes
    protected abstract int getLayoutRes();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        super.onViewCreated(view, savedInstanceState);
    }

    protected void setShowBackButtonInToolbar(boolean isShow) {
        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(isShow);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(isShow);
        }catch (Exception e) {

        }
    }

    protected void setToolbarTitle(String title) {
        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        }catch (Exception e) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
