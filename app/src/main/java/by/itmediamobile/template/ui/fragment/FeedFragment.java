package by.itmediamobile.template.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.List;

import butterknife.BindView;
import by.itmediamobile.template.R;
import by.itmediamobile.template.base.BaseMvpViewStateFragment;
import by.itmediamobile.template.model.Feed;
import by.itmediamobile.template.model.NewsFeed;
import by.itmediamobile.template.model.adapter.FeedAdapter;
import by.itmediamobile.template.ui.presenter.FeedPresenter;
import by.itmediamobile.template.ui.view.FeedView;

/**
 * Created by Denis Kholevinsky
 */

public class FeedFragment extends BaseMvpViewStateFragment<SwipeRefreshLayout, List<Feed>, FeedView, FeedPresenter> implements FeedView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private static final String ARG_SOURCE_ID = "sourceId";

    private String sourceId;

    private FeedAdapter adapter;

    public static FeedFragment newInstance(String sourceId) {
        FeedFragment fragment = new FeedFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_SOURCE_ID, sourceId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sourceId = getArguments().getString(ARG_SOURCE_ID);

        contentView.setOnRefreshListener(this);

        adapter = new FeedAdapter(new FeedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Feed feed) {
                goToNews(Uri.parse(((NewsFeed) feed).getUrl()));
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.feed_fragment;
    }

    @NonNull
    @Override
    public FeedPresenter createPresenter() {
        return new FeedPresenter();
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        contentView.setRefreshing(pullToRefresh);
    }

    @Override
    public List<Feed> getData() {
        return adapter == null ? null : adapter.getData();
    }

    @NonNull
    @Override
    public LceViewState<List<Feed>, FeedView> createViewState() {
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
    public void setData(List<Feed> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.getFeedData(sourceId, pullToRefresh);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void onDestroyView() {
        adapter = null;
        super.onDestroyView();
    }

    @Override
    public void goToNews(Uri uri) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            getContext().startActivity(intent);
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
