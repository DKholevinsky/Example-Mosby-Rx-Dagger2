package by.itmediamobile.template.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import by.itmediamobile.template.R;
import by.itmediamobile.template.base.BaseMvpViewStateFragment;
import by.itmediamobile.template.model.Source;
import by.itmediamobile.template.model.adapter.SourceAdapter;
import by.itmediamobile.template.ui.event.FragmentChangeEvent;
import by.itmediamobile.template.ui.presenter.SourcePresenter;
import by.itmediamobile.template.ui.view.SourceView;

/**
 * Created by Denis Kholevinsky
 */

public class SourceFragment extends BaseMvpViewStateFragment<SwipeRefreshLayout, List<Source>, SourceView, SourcePresenter> implements SourceView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private static final String ARG_CATEGORY = "category";
    private String category;

    private SourceAdapter adapter;

    public static SourceFragment newInstance(String category) {
        SourceFragment fragment = new SourceFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        category = getArguments().getString(ARG_CATEGORY);

        contentView.setOnRefreshListener(this);

        adapter = new SourceAdapter(new SourceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Source source) {
                goToNews(source.getId());
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.source_fragment;
    }

    @Override
    public SourcePresenter createPresenter() {
        return new SourcePresenter();
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        contentView.setRefreshing(pullToRefresh);
    }

    @Override
    public List<Source> getData() {
        return adapter == null ? null : adapter.getData();
    }

    @Override
    public LceViewState<List<Source>, SourceView> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(pullToRefresh);
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setData(List<Source> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.getData(category, pullToRefresh);
    }


    @Override
    public void goToNews(String sourceId) {
        EventBus.getDefault().post(new FragmentChangeEvent(FeedFragment.newInstance(sourceId)));
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }
}
