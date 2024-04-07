package model;

import java.util.ArrayList;

public class order {

    private customer cst;

    private int number;

    private driver drv;

    private provider prv;

    private ArrayList<product> items;


    public order(customer cst, driver drv, provider prv, ArrayList<product> items, int number) {
        this.cst = cst;
        this.drv = drv;
        this.prv = prv;
        this.items = items;
        this.number = number;
    }

    public customer getCustomer() {
        return cst;
    }

    public void setCustomer(customer cst) {
        this.cst = cst;
    }

    public driver getDriver() {
        return drv;
    }

    public void setDriver(driver drv) {
        this.drv = drv;
    }

    public provider getProvider() {
        return prv;
    }

    public void setProvider(provider prv) {
        this.prv = prv;
    }

    public ArrayList<product> getItems() {
        return items;
    }

    public void setItems(ArrayList<product> items) {
        this.items = items;
    }

    public void addItem(product p){
        this.items.add(p);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
