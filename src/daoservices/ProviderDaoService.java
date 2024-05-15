package daoservices;

import dao.ProviderDao;
import model.Provider;

import java.sql.SQLException;

public class ProviderDaoService {
    private ProviderDao providerDao = ProviderDao.getInstance();

    public ProviderDaoService() throws SQLException {
    }
    public Provider getproviderByName(String name) throws SQLException {
        Provider p = providerDao.read(name);
        if(p != null){
            System.out.println("Name: " + p.getName());
            System.out.println("Location: " + p.getAddress());
            System.out.println("Phone number: " + p.getPhoneNumber());
        }
        else{
            System.out.println("No provider with this name");
        }
        return p;
    }

    public void removeprovider(String name) throws SQLException {
        Provider p = getproviderByName(name);
        if(p == null) return;
        providerDao.delete(p);
        System.out.println("Removed provider " + name);
    }

    public void addprovider(Provider p) throws SQLException {
        if(p != null){
            providerDao.create(p);
            System.out.println("Provider successfully added");
        }
    }
}
