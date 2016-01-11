package net.dragora.mttweather.ui.city_weather;

import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.db.chart.Tools;
import com.db.chart.listener.OnEntryClickListener;
import com.db.chart.model.BarSet;
import com.db.chart.model.ChartSet;
import com.db.chart.model.LineSet;
import com.db.chart.view.LineChartView;
import com.db.chart.view.Tooltip;

import net.dragora.mttweather.R;
import net.dragora.mttweather.pojo.weather.Hourly;
import net.dragora.mttweather.pojo.weather.Weather;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by nietzsche on 10/01/16.
 */
@EViewGroup(R.layout.city_weather_forecast_view)
public class ForecastView extends CardView {
    private static final SimpleDateFormat inputSdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat outSdf = new SimpleDateFormat("EEEE dd");
    @ViewById
    TextView title;
    @ViewById
    LineChartView chart;
    TextView toolTipText;
    private Tooltip mTip;
    private Weather weather;

    public ForecastView(Context context) {
        super(context);
    }

    public ForecastView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForecastView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterViews
    protected void afterViews() {
        // Tooltip
        mTip = new Tooltip(getContext(), R.layout.linechart_three_tooltip, R.id.value);


        mTip.setVerticalAlignment(Tooltip.Alignment.BOTTOM_TOP);
        mTip.setDimensions((int) Tools.fromDpToPx(28), (int) Tools.fromDpToPx(28));


        mTip.setEnterAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 1),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f),
                PropertyValuesHolder.ofFloat(View.SCALE_X, 1f),
                PropertyValuesHolder.ofFloat(View.ROTATION, 360)
        ).setDuration(200);

        mTip.setExitAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 0),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f),
                PropertyValuesHolder.ofFloat(View.SCALE_X, 0f),
                PropertyValuesHolder.ofFloat(View.ROTATION, -360)
        ).setDuration(200);

        mTip.setPivotX(Tools.fromDpToPx(28) / 2);
        mTip.setPivotY(Tools.fromDpToPx(28));
        toolTipText = (TextView) mTip.findViewById(R.id.value);

        chart.setTooltips(mTip);
        chart.setOnEntryClickListener((setIndex, entryIndex, rect) -> {
            chart.dismissAllTooltips();
            mTip.prepare(rect, weather.getHourlies().get(entryIndex).getTempC());
            chart.showTooltip(mTip, true);
        });
    }

    public void bind(@NonNull Weather weather) {
        this.weather = weather;

        String[] labels = new String[weather.getHourlies().size()];
        float[] values = new float[labels.length];
        for (int i = 0; i < values.length; i++) {
            Hourly hourly = weather.getHourlies().get(i);
            labels[i] = String.valueOf(hourly.getTime() / 100);

            values[i] = hourly.getTempC();
        }

        float[] valuesSorted = Arrays.copyOf(values, values.length);
        Arrays.sort(valuesSorted);
        Float max = valuesSorted[valuesSorted.length - 1];
        Float min = valuesSorted[0];

        LineSet chartSet = new LineSet(labels, values);

        chartSet.setColor(getContext().getResources().getColor(R.color.colorPrimary));
        chartSet.setDotsColor(getContext().getResources().getColor(R.color.md_blue_100));
        chartSet.setDotsRadius(getContext().getResources().getDimensionPixelSize(R.dimen.chart_dots_radius));
        chartSet.setThickness(getContext().getResources().getDimensionPixelSize(R.dimen.chart_thick));
        chartSet.setDotsStrokeColor(getContext().getResources().getColor(R.color.colorPrimary));
        chartSet.setDotsStrokeThickness(getContext().getResources().getDimensionPixelSize(R.dimen.chart_dots_tick));
        chart.getData().clear();
        chart.addData(chartSet);
        chart.setStep(5);
        chart.setAxisBorderValues(Math.round(min), Math.round(max + 1));
        chart.setAxisColor(getContext().getResources().getColor(R.color.md_grey_600));
        chart.setLabelsColor(getContext().getResources().getColor(R.color.md_grey_600));
        chart.dismissAllTooltips();
        chart.show();


        String dateLabel = weather.getDate();
        try {
            Date date = inputSdf.parse(dateLabel);
            dateLabel = outSdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        title.setText(dateLabel);

    }
}
