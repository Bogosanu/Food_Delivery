package dao;

import model.driver;

import java.util.ArrayList;

public class driverdao {

    private static ArrayList<driver> drivers = new ArrayList<>();

    public driver read(String first_name, String last_name){
        if(!drivers.isEmpty()){
            for(driver d : drivers){
                if(d.getFirst_name().equals(first_name) && d.getLast_name().equals(last_name))
                    return d;
            }
        }
        return null;
    }

    public void delete(driver d){
        drivers.remove(d);
    }

    public void create(driver d){
        drivers.add(d);
    }
}
