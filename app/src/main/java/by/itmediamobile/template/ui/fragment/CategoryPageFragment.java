package by.itmediamobile.template.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import by.itmediamobile.template.R;
import by.itmediamobile.template.base.BaseMvpFragment;
import by.itmediamobile.template.model.SourceCategory;
import by.itmediamobile.template.model.adapter.CategoryPageAdapter;
import by.itmediamobile.template.ui.presenter.CategoryPagePresenter;
import by.itmediamobile.template.ui.view.CategoryPageView;

/**
 * Created by Denis Kholevinsky
 */

public class CategoryPageFragment extends BaseMvpFragment<CategoryPageView, CategoryPagePresenter> implements CategoryPageView{

    private CategoryPageAdapter adapter;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new CategoryPageAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        loadData(false);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.category_view_pager_fragment;
    }

    @Override
    public CategoryPagePresenter createPresenter() {
        return new CategoryPagePresenter(getContext());
    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setData(List<SourceCategory> data) {
        adapter.setCategories(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        Log.d("TTT", "loadData()");
        presenter.getData();
    }
}
