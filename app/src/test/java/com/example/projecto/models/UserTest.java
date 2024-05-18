package com.example.projecto.models;

import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @org.junit.Before
    public void setUp() {
        user = User.getInstance("Test Name", "test@example.com", "password123");
    }

    @org.junit.Test
    public void getInstance_singletonInstance() {
        // Retrieve the User instance twice
        User firstInstance = User.getInstance();
        User secondInstance = User.getInstance();

        // Check if both instances point to the same object
        assertSame(firstInstance, secondInstance);
    }

    @org.junit.Test
    public void userProperties_correctlySet() {
        // Test to check if the properties are set correctly using the overloaded getInstance
        assertEquals("Test Name", user.getName());
        assertEquals( "test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }

    @org.junit.Test
    public void userProperties_changeReflectsAcrossInstances() {
        // Change properties using one instance and check if the change reflects on another instance
        user.setName("New Name");
        user.setEmail("newemail@example.com");
        user.setPassword("newpassword");

        // Get a new reference to the singleton instance
        User newUserInstance = User.getInstance();
        assertEquals("New Name", newUserInstance.getName());
        assertEquals("newemail@example.com", newUserInstance.getEmail());
        assertEquals("newpassword", newUserInstance.getPassword());
    }

    @org.junit.Test
    public void userProperties_updated() {
        // Update the user properties
        user.setName("Updated Name");
        user.setEmail("updated@example.com");
        user.setPassword("updatedPassword");

        // Assert that the properties have been updated correctly
        assertEquals("Updated Name", user.getName());
        assertEquals("updated@example.com", user.getEmail());
        assertEquals("updatedPassword", user.getPassword());
    }

    @org.junit.Test
    public void resetSingletonInstance_methodFunctionality() {
        // Assuming you have a method to reset the singleton instance (for testing purposes)
        User.resetInstance(); // Method to reset the singleton instance
        User newUserInstance = User.getInstance();

        assertNotSame(user, newUserInstance);
    }

}