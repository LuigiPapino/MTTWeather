package net.dragora.mttweather.ui.city_weather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.dragora.mttweather.R;
import net.dragora.mttweather.pojo.weather.CityWeather;
import net.dragora.mttweather.pojo.weather.CurrentCondition;
import net.dragora.mttweather.pojo.weather.Weather;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by nietzsche on 10/01/16.
 */
@EViewGroup(R.layout.city_weather_header_view)
public class HeaderView extends CardView {

    @ViewById
    ImageView currentIcon;
    @ViewById
    TextView currentTemp;
    @ViewById
    TextView currentArea;
    @ViewById
    TextView currentTempFeel;
    @ViewById
    TextView currentVisibility;
    @ViewById
    TextView currentWindSpeed;
    @ViewById
    TextView currentPrecipitations;
    @ViewById
    TextView currentHumidity;
    @ViewById
    TextView currentPressure;
    @ViewById
    RelativeLayout currentLayout;
    @ViewById
    ProgressBar progressBar;
    public HeaderView(Context context) {
        super(context);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bind(@NonNull CityWeather weather) {
        CurrentCondition currentCondition = weather.getDatum().getCurrentConditions().get(0);
        Glide.with(getContext())
                .load(currentCondition.getWeatherIconUrls().get(0).getValue())
                .crossFade()
                .into(currentIcon);
        currentTemp.setText(getContext().getString(R.string.temp, currentCondition.getTempC()));
        currentArea.setText(weather.getDatum().getNearestAreas().get(0).getAreaNames().get(0).getValue());
        currentTempFeel.setText(getContext().getString(R.string.feels_like, currentCondition.getFeelsLikeC()));
        currentVisibility.setText(getContext().getString(R.string.visibility, currentCondition.getVisibility()));
        currentWindSpeed.setText(getContext().getString(R.string.wind_speed, currentCondition.getWindspeedKmph()));
        currentPrecipitations.setText(getContext().getString(R.string.precipitations_mm, currentCondition.getPrecipMM()));
        currentHumidity.setText(getContext().getString(R.string.humidity, currentCondition.getHumidity()));
        currentPressure.setText(getContext().getString(R.string.pressure, currentCondition.getPressure()));
    }
}
