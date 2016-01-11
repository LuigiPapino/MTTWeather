package net.dragora.mttweather.ui.search;

import android.content.Context;
import android.view.ViewGroup;

import net.dragora.mttweather.pojo.UserSettings;
import net.dragora.mttweather.pojo.search_city.Result;
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
public class SearchCityAdapter extends UltimateRecyclerViewAdapterBase<Result, ItemCityView> {

    @RootContext
    Context context;
    private boolean showUndo = false;
    private UserSettings userSettings = new UserSettings();
    private PublishSubject<UserSettings> userSettingsPublishSubject;
    private boolean isClickable;

    public boolean isClickable() {
        return isClickable;
    }

    public void setClickable(boolean clickable) {
        isClickable = clickable;
    }

    public boolean isShowUndo() {
        return showUndo;
    }

    public void setShowUndo(boolean showUndo) {
        this.showUndo = showUndo;
    }

    public void setItems(List<Result> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    protected ItemCityView onCreateItemView(ViewGroup parent, int viewType) {
        return ItemCityView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<ItemCityView> holder, int position) {
        ItemCityView view = holder.getView();
        Result item = items.get(position);
        view.bind(item, userSettings, userSettingsPublishSubject, showUndo, isClickable);
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public void setUserSettingsPublishSubject(PublishSubject<UserSettings> userSettingsPublishSubject) {
        this.userSettingsPublishSubject = userSettingsPublishSubject;
    }
}
