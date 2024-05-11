package model;

import java.util.HashSet;

public class Provider {
    private String name;
    private String phoneNumber;
    private String address;

    private HashSet<Product> availableProducts;


    public Provider(String name, String phoneNumber, String address, HashSet <Product> availableProducts){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.availableProducts = availableProducts;
    }

    public Provider(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.availableProducts = new HashSet<>();
    }

    public HashSet<Product> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(HashSet<Product> availableProducts) {
        this.availableProducts = availableProducts;
    }

    public String getName() {
        return name;
    }

    public void addAvailable_product(Product p){
        this.availableProducts.add(p);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
