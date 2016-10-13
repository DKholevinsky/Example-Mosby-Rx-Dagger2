package by.itmediamobile.template.di.module;

import by.itmediamobile.template.di.scope.DataScope;
import by.itmediamobile.template.repository.DefaultFeedRepository;
import by.itmediamobile.template.repository.FeedRepository;
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

}
