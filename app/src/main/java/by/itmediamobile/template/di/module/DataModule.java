package by.itmediamobile.template.di.module;

import by.itmediamobile.template.di.scope.DataScope;
import by.itmediamobile.template.repository.DefaultFeedRepository;
import by.itmediamobile.template.repository.DefaultSourceRepository;
import by.itmediamobile.template.repository.FeedRepository;
import by.itmediamobile.template.repository.SourceRepository;
import by.itmediamobile.template.service.ApiService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis Kholevinsky
 */

@Module
public class DataModule {

    @DataScope
    @Provides
    FeedRepository provideFeedRepository(ApiService service) {
        return new DefaultFeedRepository(service);
    }

    @DataScope
    @Provides
    SourceRepository provideSourceRepository(ApiService service) {
        return new DefaultSourceRepository(service);
    }

}
