package net.dragora.mttweather.ui.city_weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import net.dragora.mttweather.R;
import net.dragora.mttweather.ui.BaseActivity;
import net.dragora.mttweather.ui.city_list.CityListActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

/**
 * An activity representing a single City detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link CityListActivity}.
 */
@EActivity(R.layout.city_weather_activity)
public class CityWeatherActivity extends BaseActivity {

    @ViewById
    Toolbar detailToolbar;

    @ViewById
    AppBarLayout appBar;
    @ViewById
    FrameLayout cityDetailContainer;


    @Extra
    String weatherId = "";
    private boolean shouldAddFragment = false;

    @AfterViews
    void setup() {
        setSupportActionBar(detailToolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (shouldAddFragment) {
            CityWeatherFragment fragment = CityWeatherFragment_.builder().weatherId(weatherId).build();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.city_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shouldAddFragment = savedInstanceState == null;

    }


    @OptionsItem(android.R.id.home)
    void home() {
        navigateUpTo(new Intent(this, CityListActivity.class));
    }

}
