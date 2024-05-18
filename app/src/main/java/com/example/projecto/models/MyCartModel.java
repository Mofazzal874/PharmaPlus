package com.example.projecto.models;

public class MyCartModel {
    private static MyCartModel instance;

    private String productName;
    private String productPrice;
    private String currentDate;
    private String currentTime;
    private String totalQuantity;
    private double totalPrice;
    private String documentId;

    // Private constructor to prevent instantiation
    private MyCartModel() {}

    // Static method to get the singleton instance
    public static synchronized MyCartModel getInstance() {
        if (instance == null) {
            instance = new MyCartModel();
        }
        return instance;
    }

    // Overloaded method to set initial values
    public static synchronized MyCartModel getInstance(String productName, String productPrice, String currentDate, String currentTime, String totalQuantity, double totalPrice) {
        MyCartModel instance = getInstance();
        instance.setProductName(productName);
        instance.setProductPrice(productPrice);
        instance.setCurrentDate(currentDate);
        instance.setCurrentTime(currentTime);
        instance.setTotalQuantity(totalQuantity);
        instance.setTotalPrice(totalPrice);
        return instance;
    }

    // Getters and Setters
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
