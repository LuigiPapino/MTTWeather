package net.dragora.mttweather.ui.search;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import net.dragora.mttweather.R;
import net.dragora.mttweather.pojo.UserSettings;
import net.dragora.mttweather.pojo.search_city.Result;
import net.dragora.mttweather.viewmodels.CityListViewModel;
import net.dragora.mttweather.viewmodels.CitySearchViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reark.reark.utils.Preconditions;
import io.reark.reark.utils.RxViewBinder;
import io.reark.reark.utils.TextWatcherObservable;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by nietzsche on 09/01/16.
 */
@EViewGroup(R.layout.search_city_view)
public class SearchCityView extends RelativeLayout {

    private static final String TAG = SearchCityView.class.getSimpleName();
    @ViewById
    EditText searchInput;
    @ViewById
    TextView statusText;
    @ViewById
    UltimateRecyclerView ultimateRecyclerView;
    @ViewById
    ProgressBar progressBar;
    @ViewById
    ImageView imageStatusError;
    @ViewById
    RelativeLayout searchLayout;


    @Bean
    SearchCityAdapter adapter;
    private Observable<String> searchStringObservable;

    public SearchCityView(Context context) {
        super(context);
    }

    public SearchCityView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchCityView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SearchCityView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @AfterViews
    protected void afterViews() {
        searchStringObservable = TextWatcherObservable.create(searchInput);
        ultimateRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ultimateRecyclerView.setAdapter(adapter);
    }


    private void setNetworkRequestStatus(@NonNull CitySearchViewModel.ProgressStatus networkRequestStatus) {
        Preconditions.checkNotNull(networkRequestStatus, "Network Request Status cannot be null.");
        Log.d(TAG, "setNetworkRequestStatus() called with: " + "networkRequestStatus = [" + networkRequestStatus + "]");

        String networkStatusText = "";
        progressBar.setVisibility(INVISIBLE);
        progressBar.setIndeterminate(false); //useful with espresso
        imageStatusError.setVisibility(INVISIBLE);

        switch (networkRequestStatus) {

            case LOADING:
                networkStatusText = "";
                progressBar.setVisibility(VISIBLE);
                progressBar.setIndeterminate(true);
                imageStatusError.setVisibility(INVISIBLE);
                break;
            case ERROR:
                networkStatusText = getContext().getString(R.string.error_occured);
                progressBar.setVisibility(INVISIBLE);
                progressBar.setIndeterminate(false);
                imageStatusError.setVisibility(VISIBLE);
                break;
            case IDLE:
                networkStatusText = "";
                progressBar.setVisibility(INVISIBLE);
                progressBar.setIndeterminate(false); //useful with espresso
                imageStatusError.setVisibility(INVISIBLE);
                break;
        }
        setNetworkRequestStatusText(networkStatusText);
    }

    private void setNetworkRequestStatusText(@NonNull String networkRequestStatusText) {
        Preconditions.checkNotNull(networkRequestStatusText, "Network Status Text cannot be null.");
        Preconditions.checkState(statusText != null, "Status Text View should not be null.");

        statusText.setText(networkRequestStatusText);
    }

    private void setResults(@NonNull List<Result> results) {
        Log.d(TAG, "setResults() called with: " + "results = [" + results + "]");
        Preconditions.checkNotNull(results, "Result List Text cannot be null.");
        Preconditions.checkState(adapter != null, "List Adapter should not be null.");

        adapter.setItems(results);
    }

    private void setUserSettings(@NonNull UserSettings userSettings) {
        adapter.setUserSettings(userSettings);
    }

    public static class SearchViewBinder extends RxViewBinder {
        private SearchCityView view;
        private CitySearchViewModel viewModel;

        public SearchViewBinder(@NonNull final SearchCityView view,
                                @NonNull final CitySearchViewModel viewModel) {
            Preconditions.checkNotNull(view, "View cannot be null.");
            Preconditions.checkNotNull(viewModel, "ViewModel cannot be null.");

            this.view = view;
            this.viewModel = viewModel;
        }

        @Override
        protected void bindInternal(@NonNull final CompositeSubscription s) {
            view.adapter.setShowUndo(false);
            view.adapter.setClickable(false);
            s.add(viewModel.getResults()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(view::setResults));
            s.add(viewModel.getNetworkRequestStatusText()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(view::setNetworkRequestStatus));
            s.add(view.searchStringObservable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(viewModel::setSearchString));
            s.add(viewModel.getUserSettings()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(view::setUserSettings));

            view.adapter.setUserSettingsPublishSubject(viewModel.getUserSettingsPublishSubject());

        }

    }

    public static class ListViewBinder extends RxViewBinder {
        private SearchCityView view;
        private CityListViewModel viewModel;

        public ListViewBinder(@NonNull final SearchCityView view,
                              @NonNull final CityListViewModel viewModel) {
            Preconditions.checkNotNull(view, "View cannot be null.");
            Preconditions.checkNotNull(viewModel, "ViewModel cannot be null.");

            this.view = view;
            this.viewModel = viewModel;
        }

        @Override
        protected void bindInternal(@NonNull final CompositeSubscription s) {
            view.adapter.setShowUndo(true);
            view.adapter.setClickable(true);

            s.add(viewModel.getUserSettings()
                    .map(UserSettings::getFavoriteCityResults)
                    .map(ArrayList::new)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(view::setResults));
            s.add(viewModel.getUserSettings()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(view::setUserSettings));
            view.adapter.setUserSettingsPublishSubject(viewModel.getUserSettingsPublishSubject());
            view.searchLayout.setVisibility(GONE);
        }

    }
}
