package by.itmediamobile.template.service;

import by.itmediamobile.template.model.apimodel.ArticlesApiModel;
import by.itmediamobile.template.model.apimodel.SourcesApiModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Denis Kholevinsky
 */

public interface ApiService {

    @GET("articles")
    Observable<ArticlesApiModel> getArticles(
            @Query("source") String source,
            @Query("apiKey") String apiKey
    );

    @GET("sources")
    Observable<SourcesApiModel> getSources(
            @Query("category") String category
    );

}
