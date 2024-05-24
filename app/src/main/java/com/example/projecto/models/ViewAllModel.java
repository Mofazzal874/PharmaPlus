package com.example.projecto.models;

import com.example.projecto.observer.Observer;
import com.example.projecto.observer.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ViewAllModel implements Subject, Serializable{
    private static ViewAllModel instance;

    private List<Observer> observers = new ArrayList<>();

    // Existing fields
    private String name, gname, description, img_url, type, discount;
    private double price;

    private ViewAllModel() {}

    public static synchronized ViewAllModel getInstance() {
        if (instance == null) {
            instance = new ViewAllModel();
        }
        return instance;
    }

    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    // Call notifyObservers whenever a product is updated, added, or removed
    public void setProductDetails(String name, String gname, double price, String description, String img_url, String type, String discount) {
        this.name = name;
        this.gname = gname;
        this.price = price;
        this.description = description;
        this.img_url = img_url;
        this.type = type;
        this.discount = discount;
        notifyObservers();
    }

    // Method to reset the singleton instance for testing
    public static void resetInstance() { instance = null; }


    // Existing getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyObservers();
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
        notifyObservers();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
        notifyObservers();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        notifyObservers();
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
        notifyObservers();
    }
}
