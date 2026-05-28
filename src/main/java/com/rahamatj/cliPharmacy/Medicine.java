package com.rahamatj.cliPharmacy;

public class Medicine {

    String name;
    double price;
    String expiry;
    int quantity;

    Medicine(String name, double price, String expiry, int quantity) {
        this.name = name;
        this.price = price;
        this.expiry = expiry;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getExpiry() {
        return expiry;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nPrice: " + price + "\nExpiry: " + expiry + "\nQuantity: " + quantity;
    }
}
