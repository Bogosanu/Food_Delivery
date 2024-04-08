package daoservices;

import dao.providerdao;
import model.provider;

public class providerdaoservice {
    private providerdao providerDao;

    public providerdaoservice() {
        this.providerDao = new providerdao();
    }
    public provider getproviderByName(String name){
        provider p = providerDao.read(name);
        if(p != null){
            System.out.println(p);
        }
        else{
            System.out.println("No provider with this name");
        }
        return p;
    }

    public void removeprovider(String name){
        provider p = getproviderByName(name);
        if(p == null) return;
        providerDao.delete(p);
        System.out.println("Removed provider " + name);
    }

    public void addprovider(provider p){
        if(p != null){
            providerDao.create(p);
            System.out.println("Provider successfully added");
        }
    }
}
