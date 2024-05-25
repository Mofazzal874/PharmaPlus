package com.example.projecto.activities;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import com.example.projecto.MainActivity;
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
public class MycartsFragmentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

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
    public void testBuyNowButton_opensAddressActivity() {
        // Launch the MainActivity
        ActivityScenario<MainActivity> scenario = activityScenarioRule.getScenario();
        scenario.onActivity(activity -> {
            // Find the NavController from the activity
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
            // Navigate to MycartsFragment
            navController.navigate(R.id.nav_mycarts);
        });

        // Perform click on buy_now button
        onView(withId(R.id.buy_now)).perform(click());

        // Verify that the AddressActivity is launched
        intended(hasComponent(AddressActivity.class.getName()));
    }
}
