package by.itmediamobile.template.di.module;

import by.itmediamobile.template.di.scope.ApiScope;
import by.itmediamobile.template.service.ApiService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Denis Kholevinsky
 */

@Module
public class ApiModule {

    @Provides
    @ApiScope
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
