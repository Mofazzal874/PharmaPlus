package com.example.projecto.activities;

import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.projecto.R;
import com.example.projecto.models.ViewAllModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityScenarioRule<DetailActivity> activityRule = new ActivityScenarioRule<>(launchDetailActivityWithIntent());

    private static Intent launchDetailActivityWithIntent() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), DetailActivity.class);
        ViewAllModel viewAllModel = ViewAllModel.getInstance();
        viewAllModel.setName("Product Name");
        viewAllModel.setGname("Generic Name");
        viewAllModel.setPrice(100.0);
        viewAllModel.setDescription("Product Description");
        viewAllModel.setImg_url("https://example.com/image.jpg");
        viewAllModel.setType("Type");
        viewAllModel.setDiscount("10%");
        intent.putExtra("detail", viewAllModel);
        return intent;
    }

    @Test
    public void testInitialProductDetailsDisplay() {
        // Verify if the product details are displayed
        Espresso.onView(ViewMatchers.withId(R.id.detail_img))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.detail_name))
                .check(ViewAssertions.matches(ViewMatchers.withText("Product Name")));
        Espresso.onView(ViewMatchers.withId(R.id.detail_price))
                .check(ViewAssertions.matches(ViewMatchers.withText("Unit Price 100.0 Tk")));
        // Add checks for other details as needed
    }

    @Test
    public void testQuantityIncrement() {
        // Perform a click on the addItem button
        Espresso.onView(ViewMatchers.withId(R.id.add_item))
                .perform(ViewActions.click());

        // Verify if the quantity is incremented
        Espresso.onView(ViewMatchers.withId(R.id.quantity))
                .check(ViewAssertions.matches(ViewMatchers.withText("2")));
    }

    @Test
    public void testQuantityDecrement() {
        // Set the initial quantity to 2
        Espresso.onView(ViewMatchers.withId(R.id.add_item))
                .perform(ViewActions.click());

        // Perform a click on the removeItem button
        Espresso.onView(ViewMatchers.withId(R.id.remove_item))
                .perform(ViewActions.click());

        // Verify if the quantity is decremented
        Espresso.onView(ViewMatchers.withId(R.id.quantity))
                .check(ViewAssertions.matches(ViewMatchers.withText("1")));
    }
}
