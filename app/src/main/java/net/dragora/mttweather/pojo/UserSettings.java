package net.dragora.mttweather.pojo;

import android.content.Context;

import com.annimon.stream.Stream;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.dragora.mttweather.R;
import net.dragora.mttweather.pojo.search_city.Result;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by ttuo on 06/04/15.
 */
public class UserSettings {
    private TreeSet<Result> favoriteCityResults = new TreeSet<>();

    public UserSettings() {
    }

    public static UserSettings init(Context context) {
        UserSettings userSettings = new UserSettings();
        Type type = new TypeToken<TreeSet<Result>>() {
        }.getType();
        InputStream is = context.getResources().openRawResource(R.raw.user_settings_init);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));


            TreeSet<Result> results = new GsonBuilder().disableHtmlEscaping().create().fromJson(reader, type);

            userSettings.favoriteCityResults = results;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userSettings;
    }

    public TreeSet<Result> getFavoriteCityResults() {
        return favoriteCityResults;
    }

    public TreeSet<Result> addFavoriteCityResult(Result cityResult) {
        getFavoriteCityResults().add(cityResult);
        return favoriteCityResults;
    }

    public TreeSet<Result> removeFavoriteCityResult(Result cityResult) {
        favoriteCityResults.remove(cityResult);
        return favoriteCityResults;
    }

    public boolean isFavoriteCityResult(Result cityResult) {
        return Stream.of(favoriteCityResults)
                .anyMatch(value -> value.equals(cityResult));
    }
}
