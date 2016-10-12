package by.itmediamobile.template.di.component;

import javax.inject.Singleton;

import by.itmediamobile.template.di.module.NetworkModule;
import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Denis Kholevinsky
 */

@Singleton
@Component(
        modules = {
                NetworkModule.class
        }
)
public interface NetworkComponent {

    Retrofit retrofit();

}
