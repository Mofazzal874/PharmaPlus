package com.example.projecto.models;

public class CategoryModel {
    private static CategoryModel instance;

    private String name;
    private String img_url;
    private String type;

    // Private constructor to prevent instantiation
    private CategoryModel() {}

    // Static method to get the singleton instance
    public static synchronized CategoryModel getInstance() {
        if (instance == null) {
            instance = new CategoryModel();
        }
        return instance;
    }

    // Overloaded method to set initial values
    public static synchronized CategoryModel getInstance(String name, String img_url, String type) {
        CategoryModel instance = getInstance();
        instance.setName(name);
        instance.setImg_url(img_url);
        instance.setType(type);
        return instance;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
