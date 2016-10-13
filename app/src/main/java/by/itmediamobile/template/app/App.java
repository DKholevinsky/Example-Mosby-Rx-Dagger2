package by.itmediamobile.template.app;

import android.app.Application;

import by.itmediamobile.template.di.component.ApiComponent;
import by.itmediamobile.template.di.component.AppComponent;
import by.itmediamobile.template.di.component.DaggerApiComponent;
import by.itmediamobile.template.di.component.DaggerAppComponent;
import by.itmediamobile.template.di.component.DaggerDataComponent;
import by.itmediamobile.template.di.component.DaggerNetworkComponent;
import by.itmediamobile.template.di.component.DataComponent;
import by.itmediamobile.template.di.component.NetworkComponent;
import by.itmediamobile.template.di.module.ApiModule;
import by.itmediamobile.template.di.module.AppModule;
import by.itmediamobile.template.di.module.DataModule;
import by.itmediamobile.template.di.module.NetworkModule;

/**
 * Created by Denis Kholevinsky
 */

public class App extends Application {

    private static AppComponent appComponent;
    private static DataComponent dataComponent;
    private static NetworkComponent networkComponent;
    private static ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        initApiComponent();
        initDataComponent();
    }

    private void initDataComponent() {
        dataComponent = DaggerDataComponent.builder()
                .apiComponent(apiComponent)
                .dataModule(new DataModule())
                .build();
    }

    private void initApiComponent() {
        apiComponent = DaggerApiComponent.builder()
                .networkComponent(initNetworkComponent())
                .apiModule(new ApiModule())
                .build();

    }

    private NetworkComponent initNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constant.BASE_URL))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static DataComponent getDataComponent() {
        return dataComponent;
    }

    public static NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
