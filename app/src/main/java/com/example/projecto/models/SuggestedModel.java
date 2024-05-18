package com.example.projecto.models;

import java.io.Serializable;

public class SuggestedModel implements Serializable {
    String name;
    String gname;
    double price;
    String description;
    String img_url;
    String type;
    String discount;

    public SuggestedModel(){}
    public SuggestedModel(String name, String gname, double price, String description, String img_url, String type, String discount) {
        this.name = name;
        this.gname = gname;
        this.price = price;
        this.description = description;
        this.img_url = img_url;
        this.type = type;
        this.discount = discount;
    }

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




