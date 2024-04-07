package dao;

import model.product;

import java.util.ArrayList;

public class productdao {
    private static ArrayList<product> products = new ArrayList<>();

    public product read(String name){
        if(!products.isEmpty()){
            for(product p : products){
                if(p.getName().equals(name))
                    return p;
            }
        }
        return null;
    }

    public void delete(product p){
        products.remove(p);
    }

    public void create(product p){
        products.add(p);
    }
}
