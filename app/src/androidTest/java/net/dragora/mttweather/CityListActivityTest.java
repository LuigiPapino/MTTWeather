package net.dragora.mttweather;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import junit.framework.Assert;

import net.dragora.mttweather.pojo.search_city.Result;
import net.dragora.mttweather.ui.city_list.CityListActivity_;
import net.dragora.mttweather.ui.search.ItemCityView;
import net.dragora.mttweather.utils.RecyclerViewClickAction;
import net.dragora.mttweather.utils.Tools;
import net.dragora.mttweather.utils.UltimateRecyclerViewAdapterBase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

/**
 * Created by nietzsche on 11/01/16.
 */
@RunWith(AndroidJUnit4.class)
public class CityListActivityTest {


    @Rule
    public ActivityTestRule<CityListActivity_> cityListActivityRule = new ActivityTestRule<>(CityListActivity_.class);


    @BeforeClass
    public static void setUp() {

    }

    @AfterClass
    public static void tearDown() {

    }


    @Test
    public void testAddCities() {

        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
        clearAllFavorites();
        onView(withId(R.id.fab)).perform(click());

        //ADD favorites
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i];
            onView(withId(R.id.search_input)).check(matches(isDisplayed()))
                    //.perform(click())
                    .perform(clearText())
                    .perform(typeText(city));

            try {
                Thread.sleep(2000); // Wait before search to not receive 429 error code (too many requests)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //onView(allOf(withId(R.id.city_name), withText(containsString(city))))                    .check(matches());
            onView(withId(R.id.ultimate_list))
                    .perform(RecyclerViewActions.scrollToPosition(0));
            onView(withId(R.id.ultimate_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, RecyclerViewClickAction.clickChildViewWithId(R.id.star_button)));


        }

        //Check if added
        Espresso.pressBack();
        Espresso.pressBack();

        onView(withText("Amsterdam")).check(matches(isDisplayed()));

        String[] citiesSorted = Arrays.copyOf(cities, cities.length);
        Arrays.sort(citiesSorted);
        UltimateRecyclerView recyclerView = (UltimateRecyclerView) Tools.getActivityInstance().findViewById(R.id.ultimate_recycler_view);
        UltimateRecyclerViewAdapterBase<Result, ItemCityView> adapter = (UltimateRecyclerViewAdapterBase<Result, ItemCityView>) recyclerView.getAdapter();
        Assert.assertEquals("Failed to add all cities", citiesSorted.length, adapter.getAdapterItemCount());
        for (int i = 0; i < citiesSorted.length; i++) {
            String city = citiesSorted[i];
            Assert.assertEquals("Failed to sort cities", adapter.getItems().get(i).getFirstAreaName(), city);
            onView(withText(city))
                    .perform(click());
            try {
                Thread.sleep(500); // Wait before search to not receive 429 error code (too many requests)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onView(allOf(withText(city), withId(R.id.current_area)))
                    .check(matches(isDisplayed()));
            Espresso.pressBack(); //TODO work only in portrait
        }



    }

    private void clearAllFavorites() {
        UltimateRecyclerView recyclerView = (UltimateRecyclerView) Tools.getActivityInstance().findViewById(R.id.ultimate_recycler_view);
        UltimateRecyclerViewAdapterBase<Result, ItemCityView> adapter = (UltimateRecyclerViewAdapterBase<Result, ItemCityView>) recyclerView.getAdapter();


        for (int i = adapter.getAdapterItemCount() - 1; i >= 0; i--) {

            String city = adapter.getItems().get(i).getFirstAreaName();
            onView(withId(R.id.ultimate_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, RecyclerViewClickAction.clickChildViewWithId(R.id.star_button)));
            try {
                Thread.sleep(500); //TODO
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onView(withText(city)).check(doesNotExist());
        }
    }

    private void clearAllFavorites() {
        UltimateRecyclerView recyclerView = (UltimateRecyclerView) Tools.getActivityInstance().findViewById(R.id.ultimate_recycler_view);
        UltimateRecyclerViewAdapterBase<Result, ItemCityView> adapter = (UltimateRecyclerViewAdapterBase<Result, ItemCityView>) recyclerView.getAdapter();


        for (int i = adapter.getAdapterItemCount() - 1; i >= 0; i--) {

            String city = adapter.getItems().get(i).getFirstAreaName();
            onView(withId(R.id.ultimate_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, RecyclerViewClickAction.clickChildViewWithId(R.id.star_button)));
            try {
                Thread.sleep(500); //TODO
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onView(withText(city)).check(doesNotExist());
        }
    }

    String[] cities = new String[]{"Rome", "Milano", "Mosca", "Dubai", "Venezia", "Los Angeles", "Paris", "Berlin", "Amsterdam"};
}
