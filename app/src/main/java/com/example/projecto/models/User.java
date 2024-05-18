package com.example.projecto.models;

public class User {
    private String name;
    private String email;
    private String password;

    // Volatile keyword ensures that multiple threads handle the uniqueInstance variable correctly
    private static volatile User uniqueInstance;

    // Private constructor prevents instantiation from other classes
    private User() {}

    // Double-checked locking for thread safety and performance
    public static User getInstance() {
        if (uniqueInstance == null) {
            synchronized (User.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new User();
                }
            }
        }
        return uniqueInstance;
    }

    // Additional constructor for setting user details
    public static User getInstance(String name, String email, String password) {
        User instance = getInstance();
        instance.setName(name);
        instance.setEmail(email);
        instance.setPassword(password);
        return instance;
    }

    public static synchronized void resetInstance() {
        uniqueInstance = null;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
