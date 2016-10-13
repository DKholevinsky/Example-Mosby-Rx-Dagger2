package by.itmediamobile.template.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import by.itmediamobile.template.R;
import by.itmediamobile.template.base.BaseMvpFragment;
import by.itmediamobile.template.model.Feed;
import by.itmediamobile.template.model.NewsFeed;
import by.itmediamobile.template.model.adapter.FeedAdapter;
import by.itmediamobile.template.ui.presenter.FeedPresenter;
import by.itmediamobile.template.ui.view.FeedView;

/**
 * Created by Denis Kholevinsky
 */

public class FeedFragment extends BaseMvpFragment<FeedView, FeedPresenter> implements FeedView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private FeedAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new FeedAdapter(new FeedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Feed feed) {
                Toast.makeText(getContext(), ((NewsFeed) feed).getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        loadData(false);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.feed_fragment;
    }

    @Override
    public FeedPresenter createPresenter() {
        return new FeedPresenter();
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
    public void setData(List<Feed> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.getFeedData();
    }
}
