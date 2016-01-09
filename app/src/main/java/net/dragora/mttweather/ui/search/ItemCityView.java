package net.dragora.mttweather.ui.search;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.dragora.mttweather.R;
import net.dragora.mttweather.pojo.search_city.Result;
import net.dragora.mttweather.pojo.search_city.SearchCity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by nietzsche on 09/01/16.
 */
@EViewGroup(R.layout.search_city_item_view)
public class ItemCityView extends RelativeLayout {
    @ViewById
    ImageView starButton;
    @ViewById
    TextView cityName;
    private Result result;
    private boolean isFavorite = false;

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

    public void bind(Result result) {

        cityName.setText(String.format("%s, %s (%s)", result.getFirstAreaName(), result.getFirstRegion(), result.getFirstCountry()));
    }

    @Click
    protected void starButton() {
        isFavorite = !isFavorite;

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
}
