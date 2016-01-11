package net.dragora.mttweather.ui.city_weather;

import android.content.Context;
import android.view.ViewGroup;

import net.dragora.mttweather.pojo.UserSettings;
import net.dragora.mttweather.pojo.search_city.Result;
import net.dragora.mttweather.pojo.weather.Weather;
import net.dragora.mttweather.ui.search.ItemCityView;
import net.dragora.mttweather.ui.search.ItemCityView_;
import net.dragora.mttweather.utils.UltimateRecyclerViewAdapterBase;
import net.dragora.mttweather.utils.ViewWrapper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import rx.subjects.PublishSubject;

/**
 * Created by nietzsche on 09/01/16.
 */
@EBean
public class ForecastAdapter extends UltimateRecyclerViewAdapterBase<Weather, ForecastView> {

    @RootContext
    Context context;


    public void setItems(List<Weather> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    protected ForecastView onCreateItemView(ViewGroup parent, int viewType) {
        return ForecastView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<ForecastView> holder, int position) {
        ForecastView view = holder.getView();
        Weather item = items.get(position);
        view.bind(item);
    }


}
