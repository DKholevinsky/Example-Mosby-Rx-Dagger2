package by.itmediamobile.template.repository;

import java.util.List;

import by.itmediamobile.template.model.Feed;
import rx.Observable;

/**
 * Created by Denis Kholevinsky
 */

public interface FeedRepository {

    Observable<List<Feed>> getFeedList();

}
