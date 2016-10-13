package by.itmediamobile.template.service;

import by.itmediamobile.template.model.Articles;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Denis Kholevinsky
 */

public interface ApiService {

    @GET("articles")
    Observable<Articles> getArticles(
            @Query("source") String source,
            @Query("apiKey") String apiKey
    );

}
