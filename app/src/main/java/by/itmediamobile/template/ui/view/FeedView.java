package by.itmediamobile.template.ui.view;

import android.net.Uri;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.itmediamobile.template.model.Feed;

/**
 * Created by Denis Kholevinsky
 */

public interface FeedView extends MvpLceView<List<Feed>> {

    void goToNews(Uri uri);

}
