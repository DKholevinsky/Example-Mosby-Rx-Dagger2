package by.itmediamobile.template.repository;

import java.util.List;

import by.itmediamobile.template.model.Source;
import by.itmediamobile.template.model.SourceCategory;
import rx.Observable;

/**
 * Created by Denis Kholevinsky
 */

public interface SourceRepository {

    Observable<List<Source>> getSourceList(SourceCategory category);

}
