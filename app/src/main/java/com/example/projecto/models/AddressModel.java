package com.example.projecto.models;

public class AddressModel {
    private static AddressModel instance;

    private String userAddress;
    private boolean isSelected;

    // Private constructor to prevent instantiation
    private AddressModel() {}

    // Static method to get the singleton instance
    public static synchronized AddressModel getInstance() {
        if (instance == null) {
            instance = new AddressModel();
        }
        return instance;
    }

    // Overloaded method to set initial values
    public static synchronized AddressModel getInstance(String userAddress, boolean isSelected) {
        AddressModel instance = getInstance();
        instance.setUserAddress(userAddress);
        instance.setSelected(isSelected);
        return instance;
    }

    // Getters and Setters
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
