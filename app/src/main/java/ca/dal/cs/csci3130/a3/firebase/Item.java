package ca.dal.cs.csci3130.a3.firebase;

import java.io.Serializable;

public class Item implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAudible() {
        return this.audible;
    }

    public void setAudible(boolean audible) {
        this.audible = audible;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public boolean isWoolen() {
        return this.woolen;
    }

    public void setWoolen(boolean woolen) {
        this.woolen = woolen;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    String name;
    String category;
    double price;
    boolean audible;
    boolean woolen;
    boolean perishable;
    String location;
    boolean isAvailable;

    public boolean isPaperback() {
        return false;
    }
}


