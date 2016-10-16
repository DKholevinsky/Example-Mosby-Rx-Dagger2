package by.itmediamobile.template.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.List;

import butterknife.BindView;
import by.itmediamobile.template.R;
import by.itmediamobile.template.base.BaseMvpViewStateFragment;
import by.itmediamobile.template.model.SourceCategory;
import by.itmediamobile.template.model.adapter.CategoryPageAdapter;
import by.itmediamobile.template.ui.presenter.CategoryPagePresenter;
import by.itmediamobile.template.ui.view.CategoryPageView;

/**
 * Created by Denis Kholevinsky
 */

public class CategoryPageFragment extends BaseMvpViewStateFragment<CoordinatorLayout, List<SourceCategory>, CategoryPageView, CategoryPagePresenter> implements CategoryPageView {

    private CategoryPageAdapter adapter;

    @BindView(R.id.contentView)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image)
    ImageView image;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolBar();
        adapter = new CategoryPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.category_view_pager_fragment;
    }

    @NonNull
    @Override
    public CategoryPagePresenter createPresenter() {
        return new CategoryPagePresenter();
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
    }

    @Override
    public List<SourceCategory> getData() {
        return adapter == null ? null : adapter.getCategories();
    }

    @NonNull
    @Override
    public LceViewState<List<SourceCategory>, CategoryPageView> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        Snackbar snackbar = Snackbar.make(coordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void setData(List<SourceCategory> data) {
        adapter.setCategories(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        adapter = null;
        super.onDestroyView();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.getData(pullToRefresh);
    }

    @Override
    public void setupToolBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setShowBackButtonInToolbar(false);
        setToolbarTitle(getString(R.string.app_name));
        Glide.with(getContext())
                .load("http://news.bbcimg.co.uk/news/special/2015/newsspec_10077/content/english/img/1024/future_of_news.jpg")
                .into(image);
    }
}
