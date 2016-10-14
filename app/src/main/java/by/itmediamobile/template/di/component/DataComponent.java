package by.itmediamobile.template.di.component;

import by.itmediamobile.template.di.module.DataModule;
import by.itmediamobile.template.di.scope.DataScope;
import by.itmediamobile.template.ui.presenter.FeedPresenter;
import by.itmediamobile.template.ui.presenter.SourcePresenter;
import dagger.Component;

/**
 * Created by Denis Kholevinsky
 */

@DataScope
@Component(
        modules = {DataModule.class},
        dependencies = {ApiComponent.class}
)
public interface DataComponent {

    void inject(FeedPresenter presenter);
    void inject(SourcePresenter presenter);

}
