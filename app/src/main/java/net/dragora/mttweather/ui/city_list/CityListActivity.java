package net.dragora.mttweather.ui.city_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import net.dragora.mttweather.R;
import net.dragora.mttweather.ui.BaseActivity;
import net.dragora.mttweather.ui.city_weather.CityWeatherActivity;
import net.dragora.mttweather.ui.city_weather.CityWeatherFragment_;
import net.dragora.mttweather.ui.search.SearchCityActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * An activity representing a list of Cities. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CityWeatherActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
@EActivity(R.layout.city_list_activity)
public class CityListActivity extends BaseActivity {

    private static final String TAG = CityListActivity.class.getSimpleName();
    @ViewById
    Toolbar toolbar;
    @ViewById
    AppBarLayout appBar;
    @ViewById
    FrameLayout listContainer, cityDetailContainer;

    @ViewById
    FloatingActionButton fab;
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane = false, shouldAddFragment = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shouldAddFragment = savedInstanceState == null;
    }

    @AfterViews
    protected void afterViews() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        // The detail container view will be present only in the
        // large-screen layouts (res/values-w900dp).
        // If this view is present, then the
        // activity should be in two-pane mode.
        if (cityDetailContainer != null)
            mTwoPane = true;

        if (shouldAddFragment) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.list_container, CityListFragment_.builder().build());
            if (mTwoPane)
                transaction.replace(R.id.city_detail_container, CityWeatherFragment_.builder().build());

            transaction.commit();


        }


    }

    @Click
    protected void fab() {
        SearchCityActivity_.intent(this)
                .start();

    }


    public boolean isTwoPane() {
        return mTwoPane;
    }
}
