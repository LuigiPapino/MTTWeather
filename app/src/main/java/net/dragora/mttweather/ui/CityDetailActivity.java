package net.dragora.mttweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import net.dragora.mttweather.R;

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
@EActivity(R.layout.activity_city_detail)
public class CityDetailActivity extends BaseActivity {

    @ViewById
    Toolbar detailToolbar;
    @ViewById
    CollapsingToolbarLayout toolbarLayout;
    @ViewById
    AppBarLayout appBar;
    @ViewById
    NestedScrollView cityDetailContainer;
    @ViewById
    FloatingActionButton fab;

    @Extra
    int itemId = -1;
    private boolean shouldAddFragment = false;

    @AfterViews
    void setup() {
        setSupportActionBar(detailToolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shouldAddFragment = savedInstanceState == null;

    }

    @AfterInject
    void afterInject() {
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
            CityDetailFragment fragment = CityDetailFragment_.builder().itemId(itemId).build();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.city_detail_container, fragment)
                    .commit();
        }
    }

    @OptionsItem(android.R.id.home)
    void home() {
        navigateUpTo(new Intent(this, CityListActivity.class));
    }

}
