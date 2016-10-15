package by.itmediamobile.template.repository;

import java.util.ArrayList;
import java.util.List;

import by.itmediamobile.template.app.Constant;
import by.itmediamobile.template.model.apimodel.ArticlesApiModel;
import by.itmediamobile.template.model.Feed;
import by.itmediamobile.template.model.apimodel.NewsApiModel;
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
    public Observable<List<Feed>> getFeedList(String sourceId) {
        return service.getArticles(sourceId, Constant.API_KEY)
                .map(new Func1<ArticlesApiModel, List<Feed>>() {
                    @Override
                    public List<Feed> call(ArticlesApiModel articlesApiModel) {
                        List<Feed> feeds = new ArrayList<>();
                        for (NewsApiModel news : articlesApiModel.getArticles()) {
                            feeds.add(new NewsFeed(news.getTitle(), news.getDescription(), news.getUrlToImage(), news.getAuthor(), news.getUrl()));
                        }
                        return feeds;
                    }
                });
    }
}
