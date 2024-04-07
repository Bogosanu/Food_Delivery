package dao;
import model.customer;

import java.util.ArrayList;

public class customerdao {

    private static ArrayList<customer> customers = new ArrayList<>();

    public customer read(String first_name, String last_name){
        if(!customers.isEmpty()){
            for(customer c : customers){
                if(c.getFirst_name().equals(first_name) && c.getLast_name().equals(last_name))
                    return c;
            }
        }
        return null;
    }

    public void delete(customer c){
        customers.remove(c);
    }

    public void create(customer c){
        customers.add(c);
    }

}
