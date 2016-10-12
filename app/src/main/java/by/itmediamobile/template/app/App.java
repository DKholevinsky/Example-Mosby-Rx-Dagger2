package by.itmediamobile.template.app;

import android.app.Application;

import by.itmediamobile.template.di.component.AppComponent;
import by.itmediamobile.template.di.component.DaggerAppComponent;
import by.itmediamobile.template.di.module.AppModule;

/**
 * Created by Denis Kholevinsky
 */

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
