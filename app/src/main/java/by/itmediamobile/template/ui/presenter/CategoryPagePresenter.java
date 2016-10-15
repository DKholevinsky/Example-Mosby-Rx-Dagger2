package by.itmediamobile.template.ui.presenter;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import by.itmediamobile.template.R;
import by.itmediamobile.template.app.App;
import by.itmediamobile.template.model.SourceCategory;
import by.itmediamobile.template.ui.view.CategoryPageView;

/**
 * Created by Denis Kholevinsky
 */

public class CategoryPagePresenter extends MvpBasePresenter<CategoryPageView> {

    private Context context;

    public CategoryPagePresenter() {
        this.context = App.getAppComponent().context();
    }

    public void getData(boolean pullToRefresh) {
        if (isViewAttached()) {
            getView().showLoading(pullToRefresh);

            getView().setData(getSourcesCategoryFromStringResources());

            getView().showContent();
        }
    }

    private List<SourceCategory> getSourcesCategoryFromStringResources() {
        List<SourceCategory> categories = new ArrayList<>();
        String[] categoryArray = context.getResources().getStringArray(R.array.sourceCategories);
        for (int i = 0; i < categoryArray.length - 1; i++) {
            categories.add(new SourceCategory(categoryArray[i]));
        }
        return categories;
    }

}
