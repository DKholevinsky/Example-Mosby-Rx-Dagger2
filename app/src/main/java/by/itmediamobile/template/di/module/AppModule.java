package by.itmediamobile.template.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis Kholevinsky
 */

@Module
public class AppModule {

    private Context appContext;

    public AppModule(@NonNull Context context) {
        this.appContext = context;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return this.appContext;
    }

}
