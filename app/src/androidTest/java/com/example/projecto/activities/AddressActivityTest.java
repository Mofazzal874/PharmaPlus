package com.example.projecto.activities;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.projecto.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class AddressActivityTest {

    @Rule
    public ActivityScenarioRule<AddressActivity> activityRule =
            new ActivityScenarioRule<>(AddressActivity.class);

    @Rule
    public FirebaseAuthRule firebaseAuthRule = new FirebaseAuthRule();

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testPaymentButton_opensPaymentActivity() {
        // Perform click on paymentBtn
        onView(withId(R.id.payment_btn)).perform(click());

        // Verify that the PaymentActivity is launched
        intended(hasComponent(PaymentActivity.class.getName()));
    }
}
