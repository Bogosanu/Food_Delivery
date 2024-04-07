package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class provider {
    private String name;
    private String phone_number;
    private String address;

    private HashSet<product> available_products;


    public provider(String name, String phone_number, String address, HashSet <product> available_products){
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.available_products = new HashSet<product>();
    }

    public String getName() {
        return name;
    }

    public void addAvailable_product(product p){
        this.available_products.add(p);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
