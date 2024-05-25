package model;

import daoservices.UserDaoService;
import service.UserService;

import java.util.*;

public class Order {

    private Customer cst;

    private int number;

    private Driver drv;

    private Provider prv;

    private List <Product> products;



    public Order(Customer cst, Driver drv, Provider prv, int number) {
        this.cst = cst;
        this.drv = drv;
        this.prv = prv;
        this.number = number;
        this.products = new ArrayList<>();
    }


    public Order(int number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return cst;
    }

    public void setCustomer(Customer cst) {
        this.cst = cst;
    }

    public Driver getDriver() {
        return drv;
    }

    public void setDriver(Driver drv) {
        this.drv = drv;
    }

    public Provider getProvider() {
        return prv;
    }

    public void setProvider(Provider prv) {
        this.prv = prv;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void addProduct(Product p){
        this.products.add(p);
    }

    public void removeProduct(Product p){
        boolean isRemoved = this.products.remove(p);
    }
}
