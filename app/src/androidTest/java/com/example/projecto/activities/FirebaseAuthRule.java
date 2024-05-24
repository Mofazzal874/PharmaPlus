package com.example.projecto.activities;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FirebaseAuthRule implements TestRule {

    private final FirebaseAuth mockAuth;
    private final FirebaseUser mockUser;

    public FirebaseAuthRule() {
        mockAuth = mock(FirebaseAuth.class);
        mockUser = mock(FirebaseUser.class);
        when(mockAuth.getCurrentUser()).thenReturn(mockUser);
        when(mockUser.getUid()).thenReturn("testUid");
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                // Inject the mock FirebaseAuth instance
                AddressActivity.setFirebaseAuth(mockAuth);

                // Execute the test
                base.evaluate();

                // Clean up after test execution
                AddressActivity.setFirebaseAuth(null);
            }
        };
    }
}
