package net.dragora.mttweather.ui.search;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.dragora.mttweather.R;
import net.dragora.mttweather.pojo.UserSettings;
import net.dragora.mttweather.pojo.search_city.Result;
import net.dragora.mttweather.pojo.search_city.SearchCity;
import net.dragora.mttweather.ui.BaseActivity;
import net.dragora.mttweather.ui.city_list.CityListActivity_;
import net.dragora.mttweather.ui.city_weather.CityWeatherActivity_;
import net.dragora.mttweather.ui.city_weather.CityWeatherFragment_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import rx.subjects.PublishSubject;

/**
 * Created by nietzsche on 09/01/16.
 */
@EViewGroup(R.layout.search_city_item_view)
public class ItemCityView extends RelativeLayout {
    private static final String TAG = ItemCityView.class.getSimpleName();
    @ViewById
    ImageView starButton;
    @ViewById
    TextView cityName;
    @ViewById
    FrameLayout cityDetailContainer;

    private Result result;
    private UserSettings userSettings;
    private boolean isFavorite = false;
    private PublishSubject<UserSettings> userSettingsPublishSubject;
    private boolean showUndo;

    public ItemCityView(Context context) {
        super(context);
    }

    public ItemCityView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemCityView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ItemCityView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void bind(@NonNull Result result,
                     @NonNull UserSettings userSettings,
                     @NonNull PublishSubject<UserSettings> userSettingsPublishSubject,
                     boolean showUndo,
                     boolean clickable) {
        this.result = result;
        this.userSettings = userSettings;
        this.userSettingsPublishSubject = userSettingsPublishSubject;
        isFavorite = userSettings.isFavoriteCityResult(result);

        if (!showUndo) //in CityListActivity not in Search
            cityName.setText(String.format("%s, %s (%s)", result.getFirstAreaName(), result.getFirstRegion(), result.getFirstCountry()));
        else
            cityName.setText(String.format("%s", result.getFirstAreaName()));

        starButton.setImageResource(isFavorite ? R.drawable.ic_star_black_36dp : R.drawable.ic_star_border_black_36dp);
        this.showUndo = showUndo;
        if (clickable)
            setOnClickListener(v -> {
                if (getContext() instanceof CityListActivity_ && ((CityListActivity_) getContext()).isTwoPane()) //TODO not much elegant
                    ((BaseActivity) getContext()).getSupportFragmentManager()
                            .beginTransaction().
                            replace(R.id.city_detail_container, CityWeatherFragment_.builder().weatherId(result.getWeatherId()).build())
                            .commit();
                else
                    CityWeatherActivity_.intent(getContext())
                            .weatherId(result.getWeatherId())
                            .start();
            });
    }

    @Click
    protected void starButton() {
        Log.d(TAG, "starButton() called with: " + "");
        isFavorite = !isFavorite;

        if (isFavorite)
            userSettings.addFavoriteCityResult(result);
        else {
            userSettings.removeFavoriteCityResult(result);
            if (showUndo)
                showUndoSnack();
        }

        userSettingsPublishSubject.onNext(userSettings);

        starButton.animate()
                .scaleX(0)
                .scaleY(0)
                .alpha(0)
                .withEndAction(() -> {
                    starButton.setImageResource(isFavorite ? R.drawable.ic_star_black_36dp : R.drawable.ic_star_border_black_36dp);
                    starButton.animate()
                            .scaleX(1)
                            .scaleY(1)
                            .alpha(1);
                });


    }

    private void showUndoSnack() {
        Snackbar.make(this, getContext().getString(R.string.favorite_city_removed, result.getFirstAreaName()), Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, v -> {
                    userSettings.addFavoriteCityResult(result);
                    userSettingsPublishSubject.onNext(userSettings);
                })
                .show();
    }
}
