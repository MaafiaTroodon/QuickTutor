package ca.dal.cs.csci3130.a3;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import ca.dal.cs.csci3130.a3.config.AppConstants;
import ca.dal.cs.csci3130.a3.firebase.Item;
import ca.dal.cs.csci3130.a3.firebase.ItemCRUD;
import ca.dal.cs.csci3130.a3.ui.DetailsActivity;

@RunWith(AndroidJUnit4.class)
public class ItemDetailsEspressoTest {

    ItemCRUD crud;
    ArrayList<Item> clothes;
    ArrayList<Item> foods;
    ArrayList<Item> books;
    ActivityScenario<DetailsActivity> activityScenario;

    @Before
    public void setup() {
        crud = new ItemCRUD();
        clothes = crud.collectRetrievedItems(AppConstants.CLOTHES);
        foods = crud.collectRetrievedItems(AppConstants.FOOD);
        books = crud.collectRetrievedItems(AppConstants.BOOK);
    }

    @Test
    public void testItemIsWoolen() {
        activityScenario = ActivityScenario.launch(DetailsActivity.class);
        activityScenario.onActivity(activity -> {
            Item item = crud.deliverTopItem(clothes);
            activity.selectedItem = item;
            activity.showItemDetails(item);
            activity.showSpecificDetails(item);
        });
        onView(withText(AppConstants.WOOLEN)).check(matches(isDisplayed()));

    }

    @Test
    public void testItemIsPerishable() {
        activityScenario = ActivityScenario.launch(DetailsActivity.class);
        activityScenario.onActivity(activity -> {
            Item item = crud.deliverTopItem(foods);
            activity.selectedItem = item;
            activity.showItemDetails(item);
            activity.showSpecificDetails(item);
        });
        onView(withText(AppConstants.PERISHABLE)).check(matches(isDisplayed()));
    }

    @Test
    public void testItemIsAudible() {
        activityScenario = ActivityScenario.launch(DetailsActivity.class);
        activityScenario.onActivity(activity -> {
            Item item = crud.deliverTopItem(books);
            activity.selectedItem = item;
            activity.showItemDetails(item);
            activity.showSpecificDetails(item);
        });
        onView(withText(AppConstants.AUDIBLE)).check(matches(isDisplayed()));
    }
}
