package by.itmediamobile.template.service;

import by.itmediamobile.template.model.User;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Denis Kholevinsky
 */

public interface ApiService {

    @GET("...")
    Observable<User> getUsers();

}
