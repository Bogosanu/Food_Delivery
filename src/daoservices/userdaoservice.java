package daoservices;

import dao.customerdao;
import dao.driverdao;
import model.customer;
import model.driver;
import model.user;

public class userdaoservice {

    private customerdao customerDao;
    private driverdao driverDao;

    public userdaoservice() {
        this.customerDao = new customerdao();
        this.driverDao = new driverdao();
    }

    public customer getCustomerByName(String first_name, String last_name){
        customer c = customerDao.read(first_name, last_name);
        if(c != null){
            System.out.println(c);
        }
        else{
            System.out.println("No customer with this name");
        }
        return c;
    }

    public driver getDriverByName(String first_name, String last_name){
        driver d = driverDao.read(first_name, last_name);
        if(d != null){
            System.out.println(d);
        }
        else{
            System.out.println("No driver with this name");
        }
        return d;
    }

    public void removeUser(String type, String first_name, String last_name){
        user usr = getUser(type, first_name, last_name);
        if(usr == null) return;

        switch (usr){
            case driver drv -> driverDao.delete(drv);
            case customer cst -> customerDao.delete(cst);
            default -> throw new IllegalStateException("Unexpected value: " + usr);
        }

        System.out.println("Removed " + usr);

    }

    public void addUser(user usr){
        if(usr != null) {
            switch (usr) {
                case driver drv -> driverDao.create(drv);
                case customer cst -> customerDao.create(cst);
                default -> throw new IllegalStateException("Unexpected value: " + usr);
            }
            System.out.println("User successfully added");
        }
    }




    public user getUser(String type, String first_name, String last_name){
        user usr;
        if(type.equals("customer")){
            usr = getCustomerByName(first_name, last_name);
        }
        else {
            usr = getDriverByName(first_name, last_name);
        }
        if(usr == null){
            System.out.println("No user with this name");
            return null;
        }
        return usr;
    }
}
