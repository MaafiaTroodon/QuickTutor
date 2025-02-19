package ca.dal.cs.csci3130.a3;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.a3.config.AppConstants;
import ca.dal.cs.csci3130.a3.ui.DashboardActivity;

@RunWith(AndroidJUnit4.class)
public class DashboardEspressoTest {

    ActivityScenario<DashboardActivity> scenario;

    @Test
    public void testStudentAccess() {
        scenario = ActivityScenario.launch(DashboardActivity.class);
        scenario.onActivity(activity -> {
        });
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(AppConstants.STUDENT))).perform(click());
        onView(withId(R.id.enterButton)).perform(click());
        onView(withId(R.id.searchTutorButton)).check(matches(isDisplayed()));
        onView(withId(R.id.adButton)).check(doesNotExist());
        onView(withId(R.id.goShoppingButton)).check(matches(isDisplayed()));
        onView(withId(R.id.manageButton)).check(doesNotExist());
    }

    @Test
    public void testTutorAccess() {
        scenario = ActivityScenario.launch(DashboardActivity.class);
        scenario.onActivity(activity -> {
        });
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(AppConstants.TUTOR))).perform(click());
        onView(withId(R.id.enterButton)).perform(click());
        onView(withId(R.id.searchTutorButton)).check(doesNotExist());
        onView(withId(R.id.adButton)).check(matches(isDisplayed()));
        onView(withId(R.id.goShoppingButton)).check(matches(isDisplayed()));
        onView(withId(R.id.manageButton)).check(doesNotExist());
    }

    @Test
    public void testAdminAccess() {
        scenario = ActivityScenario.launch(DashboardActivity.class);
        scenario.onActivity(activity -> {
        });
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(AppConstants.ADMIN))).perform(click());
        onView(withId(R.id.enterButton)).perform(click());
        onView(withId(R.id.searchTutorButton)).check(matches(isDisplayed()));
        onView(withId(R.id.adButton)).check(matches(isDisplayed()));
        onView(withId(R.id.goShoppingButton)).check(doesNotExist());
        onView(withId(R.id.manageButton)).check(matches(isDisplayed()));
    }
}
