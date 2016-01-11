package net.dragora.mttweather.ui.city_list;


import android.support.v4.app.Fragment;

import net.dragora.mttweather.MyApplication;
import net.dragora.mttweather.R;
import net.dragora.mttweather.ui.BaseFragment;
import net.dragora.mttweather.ui.search.SearchCityView;
import net.dragora.mttweather.utils.ApplicationInstrumentation;
import net.dragora.mttweather.viewmodels.CityListViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.search_city_fragment)
public class CityListFragment extends BaseFragment {


    @ViewById
    SearchCityView searchCity;

    @Inject
    CityListViewModel cityListViewModel;

    @Inject
    ApplicationInstrumentation mInstrumentation;

    private SearchCityView.ListViewBinder cityListViewBinder;

    @AfterViews
    protected void afterViews() {
        MyApplication.getInstance().getGraph().inject(this);
        cityListViewBinder = new SearchCityView.ListViewBinder(searchCity, cityListViewModel);
        cityListViewModel.subscribeToDataStore();
    }


    @Override
    public void onResume() {
        super.onResume();
        cityListViewBinder.bind();
    }

    @Override
    public void onPause() {
        super.onPause();
        cityListViewBinder.unbind();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cityListViewModel.unsubscribeFromDataStore();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cityListViewModel.dispose();
        cityListViewModel = null;
        mInstrumentation.getLeakTracing().traceLeakage(this);
    }
}
