package by.itmediamobile.template.model.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import by.itmediamobile.template.model.SourceCategory;
import by.itmediamobile.template.ui.fragment.SourceFragment;

/**
 * Created by Denis Kholevinsky
 */

public class CategoryPageAdapter extends FragmentStatePagerAdapter {

    private List<SourceCategory> categories = new ArrayList<>();

    public List<SourceCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<SourceCategory> categories) {
        this.categories = categories;
    }

    public CategoryPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SourceFragment.newInstance(categories.get(position).getName());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getName().toUpperCase();
    }

    @Override
    public int getItemPosition(Object object) {
        return CategoryPageAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return categories.size();
    }
}
