package com.example.projecto.activities;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.firebase.auth.FirebaseAuth;

import com.example.projecto.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class WelcomeTest {

    @Rule
    public ActivityScenarioRule<Welcome> activityRule =
            new ActivityScenarioRule<>(Welcome.class);

    @Test
    public void testUserButtonNavigatesCorrectly() {
        // Perform a click on the ubutton
        Espresso.onView(ViewMatchers.withId(R.id.lauser))
                .perform(ViewActions.click());

        // Verify if the MainActivity or LoginActivity is launched
        // This will depend on the current user authentication state
        // Adjust the assertion as per your requirement
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)) // replace with actual ID
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        } else {
            Espresso.onView(ViewMatchers.withId(R.id.login_layout)) // replace with actual ID
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        }
    }

    @Test
    public void testAdminButtonNavigatesCorrectly() {
        // Perform a click on the abutton
        Espresso.onView(ViewMatchers.withId(R.id.laadmin))
                .perform(ViewActions.click());

        // Verify if the AdminLoginActivity is launched
        Espresso.onView(ViewMatchers.withId(R.id.admin_login_page_id)) // replace with actual ID
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
