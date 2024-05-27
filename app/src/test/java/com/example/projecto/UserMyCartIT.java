package com.example.projecto;

import static org.junit.Assert.*;

import com.example.projecto.models.MyCartModel;
import com.example.projecto.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserMyCartIT{

    private User user;
    private MyCartModel cart;

    @Before
    public void setUp() {
        // Reset singleton instances before each test
        User.getInstance();
        MyCartModel.getInstance();

        // Initialize instances
        user = User.getInstance("John Doe", "john@example.com", "password123");
        cart = MyCartModel.getInstance("Product Name", "100", "2023-05-27", "12:00", "1", 100.0);
    }

    @Test
    public void testUserAndCartIntegration() {
        // Update user details
        user.setEmail("newemail@example.com");

        // Simulate an operation that might use updated user info
        String userEmail = user.getEmail();

        // For demonstration purposes, assume cart operations depend on user info
        cart.setProductName("Product updated for " + userEmail);

        // Verify that the cart model correctly reflects the change
        assertEquals("Product updated for newemail@example.com", cart.getProductName());
    }

    @Test
    public void testCartValues() {
        // Set new values in the cart
        cart.setTotalPrice(200.0);
        cart.setTotalQuantity("2");

        // Verify cart values
        assertEquals(200.0, cart.getTotalPrice(), 0.0);
        assertEquals("2", cart.getTotalQuantity());
    }
}
