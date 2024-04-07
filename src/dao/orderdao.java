package dao;

import model.order;

import java.util.ArrayList;

public class orderdao {
    private static ArrayList<order> orders = new ArrayList<>();

    public order read(int nr){
        if(!orders.isEmpty()){
            for(order o : orders){
                if(o.getNumber() == nr)
                    return o;
            }
        }
        return null;
    }

    public void delete(order o){
        orders.remove(o);
    }

    public void create(order o){
        orders.add(o);
    }
}
