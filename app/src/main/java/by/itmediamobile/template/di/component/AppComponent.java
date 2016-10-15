package by.itmediamobile.template.di.component;

import android.content.Context;

import javax.inject.Singleton;

import by.itmediamobile.template.di.module.AppModule;
import dagger.Component;

/**
 * Created by Denis Kholevinsky
 */

@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface AppComponent {

    Context context();

}
