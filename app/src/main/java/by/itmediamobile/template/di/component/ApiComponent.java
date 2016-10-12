package by.itmediamobile.template.di.component;

import by.itmediamobile.template.di.module.ApiModule;
import by.itmediamobile.template.di.scope.ApiScope;
import dagger.Component;

/**
 * Created by Denis Kholevinsky
 */

@ApiScope
@Component(
        modules = {ApiModule.class},
        dependencies = {NetworkComponent.class}
)
public interface ApiComponent {
}
