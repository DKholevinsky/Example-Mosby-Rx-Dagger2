package by.itmediamobile.template.repository;

import java.util.ArrayList;
import java.util.List;

import by.itmediamobile.template.model.Source;
import by.itmediamobile.template.model.SourceCategory;
import by.itmediamobile.template.model.apimodel.SourceApiModel;
import by.itmediamobile.template.model.apimodel.SourcesApiModel;
import by.itmediamobile.template.service.ApiService;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Denis Kholevinsky
 */

public class DefaultSourceRepository implements SourceRepository {

    private ApiService service;

    public DefaultSourceRepository(ApiService service) {
        this.service = service;
    }

    @Override
    public Observable<List<Source>> getSourceList(String category) {
        return service.getSources(category)
                .map(new Func1<SourcesApiModel, List<Source>>() {
                    @Override
                    public List<Source> call(SourcesApiModel sourcesApiModel) {
                        List<Source> sourceList = new ArrayList<>();
                        for (SourceApiModel source : sourcesApiModel.getSources()) {
                            sourceList.add(new Source(
                                    source.getId(),
                                    source.getName(),
                                    source.getDescription(),
                                    source.getUrl(),
                                    source.getUrlsToLogos().getMedium())
                            );
                        }
                        return sourceList;
                    }
                });
    }
}
