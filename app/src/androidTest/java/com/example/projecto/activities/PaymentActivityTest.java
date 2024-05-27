package com.example.projecto.activities;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.projecto.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PaymentActivityTest {

    @Rule
    public ActivityScenarioRule<PaymentActivity> activityRule =
            new ActivityScenarioRule<>(PaymentActivity.class);

    @Test
    public void testPayButtonNavigatesToOrderPlaced() {
        // Perform a click on the paybtn
        Espresso.onView(ViewMatchers.withId(R.id.pay_btn))
                .perform(ViewActions.click());

        // Verify if the OrderPlaced activity is launched by checking a view in OrderPlaced
        Espresso.onView(ViewMatchers.withId(R.id.order_placed_view_id)) // replace with actual ID in OrderPlaced activity
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}