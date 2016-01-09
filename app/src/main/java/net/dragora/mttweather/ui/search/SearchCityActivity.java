package net.dragora.mttweather.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import net.dragora.mttweather.R;
import net.dragora.mttweather.ui.BaseActivity;
import net.simonvt.schematic.annotation.NotNull;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.search_city_activity)
public class SearchCityActivity extends BaseActivity {

    @ViewById
    Toolbar toolbar;
    @ViewById
    FrameLayout frameLayout;


    private boolean shouldAttachFragment = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shouldAttachFragment = savedInstanceState == null;

    }

    @AfterViews
    protected void afterViews() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (shouldAttachFragment)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout, SearchCityFragment_.builder().build())
                    .commit();

    }

}
