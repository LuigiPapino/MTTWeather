package net.dragora.mttweather.pojo;

import net.dragora.mttweather.pojo.search_city.Result;

import java.util.ArrayList;

/**
 * Created by ttuo on 06/04/15.
 */
public class UserSettings {
    private final int selectedRepositoryId;
    private ArrayList<Result> cityResults = new ArrayList<>(4);

    public UserSettings(int selectedRepositoryId) {
        this.selectedRepositoryId = selectedRepositoryId;
    }

    public ArrayList<Result> getCityResults() {
        return cityResults;
    }

    public ArrayList<Result> addCityResult(Result cityResult) {
        getCityResults().add(cityResult);
        return cityResults;
    }

    public int getSelectedRepositoryId() {
        return selectedRepositoryId;
    }
}
