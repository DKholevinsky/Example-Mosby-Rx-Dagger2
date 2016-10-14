package by.itmediamobile.template.ui.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.itmediamobile.template.R;
import by.itmediamobile.template.ui.event.FragmentChangeEvent;
import by.itmediamobile.template.ui.presenter.MainActivityPresenter;
import by.itmediamobile.template.ui.view.MainActivityView;

public class MainActivity extends MvpActivity<MainActivityView, MainActivityPresenter> implements MainActivityView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        if (savedInstanceState == null) {
            presenter.choiceFragment();
        }
    }

    @NonNull
    @Override
    public MainActivityPresenter createPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    public void showChildFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }

    @Subscribe
    public void onFragmentChangeEvent(FragmentChangeEvent event) {
        showChildFragment(event.fragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
