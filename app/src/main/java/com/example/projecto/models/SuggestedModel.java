package com.example.projecto.models;

import java.io.Serializable;

public class SuggestedModel implements Serializable {
    private static SuggestedModel instance;

    private String name;
    private String gname;
    private double price;
    private String description;
    private String img_url;
    private String type;
    private String discount;

    // Private constructor to prevent instantiation
    private SuggestedModel() {}

    // Static method to get the singleton instance
    public static synchronized SuggestedModel getInstance() {
        if (instance == null) {
            instance = new SuggestedModel();
        }
        return instance;
    }

    // Overloaded method to set initial values
    public static synchronized SuggestedModel getInstance(String name, String gname, double price, String description, String img_url, String type, String discount) {
        SuggestedModel instance = getInstance();
        instance.setName(name);
        instance.setGname(gname);
        instance.setPrice(price);
        instance.setDescription(description);
        instance.setImg_url(img_url);
        instance.setType(type);
        instance.setDiscount(discount);
        return instance;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
