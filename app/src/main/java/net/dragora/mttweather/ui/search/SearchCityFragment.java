package net.dragora.mttweather.ui.search;


import android.support.v4.app.Fragment;

import net.dragora.mttweather.MyApplication;
import net.dragora.mttweather.R;
import net.dragora.mttweather.pojo.search_city.SearchCity;
import net.dragora.mttweather.ui.BaseFragment;
import net.dragora.mttweather.utils.ApplicationInstrumentation;
import net.dragora.mttweather.viewmodels.CitySearchViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.search_city_fragment)
public class SearchCityFragment extends BaseFragment {


    @ViewById
    SearchCityView searchCity;

    @Inject
    CitySearchViewModel citySearchViewModel;

    @Inject
    ApplicationInstrumentation mInstrumentation;

    private SearchCityView.ViewBinder searchCityViewBinder;

    @AfterViews
    protected void afterViews() {
        MyApplication.getInstance().getGraph().inject(this);
        searchCityViewBinder = new SearchCityView.ViewBinder(searchCity, citySearchViewModel);
        citySearchViewModel.subscribeToDataStore();
    }


    @Override
    public void onResume() {
        super.onResume();
        searchCityViewBinder.bind();
    }

    @Override
    public void onPause() {
        super.onPause();
        searchCityViewBinder.unbind();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        citySearchViewModel.unsubscribeFromDataStore();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        citySearchViewModel.dispose();
        citySearchViewModel = null;
        mInstrumentation.getLeakTracing().traceLeakage(this);
    }
}
