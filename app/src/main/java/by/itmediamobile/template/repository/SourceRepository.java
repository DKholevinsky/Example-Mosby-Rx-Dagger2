package by.itmediamobile.template.repository;

import java.util.List;

import by.itmediamobile.template.model.Source;
import rx.Observable;

/**
 * Created by Denis Kholevinsky
 */

public interface SourceRepository {

    Observable<List<Source>> getSourceList(String category);

}
