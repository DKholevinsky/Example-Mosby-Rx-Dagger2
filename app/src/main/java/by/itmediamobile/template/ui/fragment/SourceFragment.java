package by.itmediamobile.template.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import by.itmediamobile.template.R;
import by.itmediamobile.template.base.BaseMvpFragment;
import by.itmediamobile.template.model.Feed;
import by.itmediamobile.template.model.Source;
import by.itmediamobile.template.model.adapter.SourceAdapter;
import by.itmediamobile.template.ui.event.FragmentChangeEvent;
import by.itmediamobile.template.ui.presenter.SourcePresenter;
import by.itmediamobile.template.ui.view.SourceView;

/**
 * Created by Denis Kholevinsky
 */

public class SourceFragment extends BaseMvpFragment<SourceView, SourcePresenter> implements SourceView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    SourceAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new SourceAdapter(new SourceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Source source) {
                goToNews(source.getId());
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        loadData(false);
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

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setData(List<Source> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.getData();
    }


    @Override
    public void goToNews(String sourceId) {
        EventBus.getDefault().post(new FragmentChangeEvent(FeedFragment.newInstance(sourceId)));
    }
}
