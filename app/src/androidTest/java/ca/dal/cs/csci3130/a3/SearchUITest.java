package ca.dal.cs.csci3130.a3;


import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertTrue;
import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class SearchUITest {

    public String launcherPackage = "ca.dal.cs.csci3130.a3";
    public int TIME_OUT = 5000;
    UiDevice device;

    @Before
    public void setup() {
        device = UiDevice.getInstance(getInstrumentation());
        Context context = ApplicationProvider.getApplicationContext();
        Intent launcherIntent = context.getPackageManager().getLaunchIntentForPackage(launcherPackage);
        assert launcherIntent != null;
        launcherIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(launcherIntent);
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), TIME_OUT);
    }

    @Test
    public void testSearchItem() throws UiObjectNotFoundException {
        UiObject roleSpinner = device.findObject(new UiSelector().text("Select a role"));
        roleSpinner.click();
        List<UiObject2> roles = device.findObjects(By.res("android:id/text1"));
        roles.get(2).click();
        UiObject enterButton = device.findObject(new UiSelector().text("ENTER"));
        enterButton.click();
        UiObject orderButton = device.findObject(new UiSelector().text("GO SHOPPING"));
        orderButton.clickAndWaitForNewWindow();
        UiObject categorySpinner = device.findObject(new UiSelector().text("Select a category"));
        categorySpinner.click();
        List<UiObject2> categories = device.findObjects(By.res("android:id/text1"));
        categories.get(3).click();
        UiObject searchButton = device.findObject(new UiSelector().text("SEARCH"));
        searchButton.click();
        UiScrollable recyclerView = new UiScrollable(new UiSelector().scrollable(false));
        assertTrue(recyclerView.exists());
        recyclerView.scrollIntoView(new UiSelector().text("Clean Code"));
        UiObject cleanCode = device.findObject(new UiSelector().text("Clean Code"));
        assertTrue(cleanCode.exists());
    }

    @Test
    public void testShowItemDetails() throws UiObjectNotFoundException {
        UiObject roleSpinner = device.findObject(new UiSelector().text("Select a role"));
        roleSpinner.click();
        List<UiObject2> roles = device.findObjects(By.res("android:id/text1"));
        roles.get(2).click();
        UiObject enterButton = device.findObject(new UiSelector().text("ENTER"));
        enterButton.click();
        UiObject orderButton = device.findObject(new UiSelector().text("GO SHOPPING"));
        orderButton.clickAndWaitForNewWindow();
        UiObject categorySpinner = device.findObject(new UiSelector().text("Select a category"));
        categorySpinner.click();
        List<UiObject2> categories = device.findObjects(By.res("android:id/text1"));
        categories.get(3).click();
        UiObject searchButton = device.findObject(new UiSelector().text("SEARCH"));
        searchButton.click();
        UiScrollable recyclerView = new UiScrollable(new UiSelector().scrollable(false));
        assertTrue(recyclerView.exists());
        recyclerView.scrollIntoView(new UiSelector().text("Clean Code"));
        UiObject cleanCode = device.findObject(new UiSelector().text("Clean Code"));
        cleanCode.clickAndWaitForNewWindow();
        UiObject audible = device.findObject(new UiSelector().text("Audible"));
        assertTrue(audible.exists());
    }
}
