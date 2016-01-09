package net.dragora.mttweather.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.dragora.mttweather.R;
import net.dragora.mttweather.dummy.DummyContent;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * A fragment representing a single City detail screen.
 * This fragment is either contained in a {@link CityListActivity}
 * in two-pane mode (on tablets) or a {@link CityDetailActivity}
 * on handsets.
 */
@EFragment(R.layout.city_detail)
public class CityDetailFragment extends BaseFragment {


    @ViewById
    TextView cityDetail;
    @ViewById
    Toolbar detailToolbar;
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    @FragmentArg
    int itemId = -1;
    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CityDetailFragment() {
    }



    @AfterInject
    void afterInject() {
        if (itemId == -1)
            return;

        // Load the dummy content specified by the fragment
        // arguments. In a real-world scenario, use a Loader
        // to load content from a content provider.
        mItem = DummyContent.ITEM_MAP.get(itemId);
    }


    @AfterViews
    void afterViews() {
        if (mItem != null)
            cityDetail.setText(mItem.details);
        if (detailToolbar != null && mItem != null)
            detailToolbar.setTitle(mItem.content);
    }

}
