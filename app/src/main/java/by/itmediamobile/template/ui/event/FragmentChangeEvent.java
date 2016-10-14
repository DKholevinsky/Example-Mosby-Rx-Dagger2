package by.itmediamobile.template.ui.event;

import android.support.v4.app.Fragment;

/**
 * Created by Denis Kholevinsky
 */

public class FragmentChangeEvent {

    public final Fragment fragment;

    public FragmentChangeEvent(Fragment fragment) {
        this.fragment = fragment;
    }

}
