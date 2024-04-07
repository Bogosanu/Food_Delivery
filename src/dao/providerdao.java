package dao;

import model.provider;

import java.util.ArrayList;

public class providerdao {
    private static ArrayList<provider> providers = new ArrayList<>();

    public provider read(String name){
        if(!providers.isEmpty()){
            for(provider p : providers){
                if(p.getName().equals(name))
                    return p;
            }
        }
        return null;
    }

    public void delete(provider p){
        providers.remove(p);
    }

    public void create(provider p){
        providers.add(p);
    }
}
