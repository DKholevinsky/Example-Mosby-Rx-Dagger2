package by.itmediamobile.template.repository;

import java.util.ArrayList;
import java.util.List;

import by.itmediamobile.template.app.Constant;
import by.itmediamobile.template.model.Articles;
import by.itmediamobile.template.model.Feed;
import by.itmediamobile.template.model.News;
import by.itmediamobile.template.model.NewsFeed;
import by.itmediamobile.template.service.ApiService;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Denis Kholevinsky
 */

public class DefaultFeedRepository implements FeedRepository {

    private ApiService service;

    public DefaultFeedRepository(ApiService service) {
        this.service = service;
    }

    @Override
    public Observable<List<Feed>> getFeedList() {
        return service.getArticles("ars-technica", Constant.API_KEY)
                .map(new Func1<Articles, List<Feed>>() {
                    @Override
                    public List<Feed> call(Articles articles) {
                        List<Feed> feeds = new ArrayList<>();
                        for (News news : articles.getArticles()) {
                            feeds.add(new NewsFeed(news.getTitle(), news.getDescription(), news.getUrlToImage(), news.getAuthor()));
                        }
                        return feeds;
                    }
                });
    }
}
