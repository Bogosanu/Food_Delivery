package daoservices;

import dao.CustomerDao;
import dao.DriverDao;
import model.Customer;
import model.Driver;
import model.User;

import java.sql.SQLException;

public class UserDaoService {

    private CustomerDao customerDao;
    private DriverDao driverDao;

    public UserDaoService() throws SQLException {
        this.customerDao = new CustomerDao();
        this.driverDao = new DriverDao();
    }

    public Customer getCustomerByName(String first_name, String last_name) throws SQLException {
        Customer c = customerDao.read(first_name, last_name);
        if(c != null){
            System.out.println(c);
        }
        else{
            System.out.println("No customer with this name");
        }
        return c;
    }

    public Driver getDriverByName(String first_name, String last_name) throws SQLException {
        Driver d = driverDao.read(first_name, last_name);
        if(d != null){
            System.out.println(d);
        }
        else{
            System.out.println("No driver with this name");
        }
        return d;
    }

    public void removeUser(String type, String first_name, String last_name) throws SQLException {
        User usr = getUser(type, first_name, last_name);
        if(usr == null) return;

        switch (usr){
            case Driver drv -> driverDao.delete(drv);
            case Customer cst -> customerDao.delete(cst);
            default -> throw new IllegalStateException("Unexpected value: " + usr);
        }

        System.out.println("Removed " + usr);

    }

    public void addUser(User usr) throws SQLException {
        if(usr != null) {
            switch (usr) {
                case Driver drv -> driverDao.create(drv);
                case Customer cst -> customerDao.create(cst);
                default -> throw new IllegalStateException("Unexpected value: " + usr);
            }
            //System.out.println("User successfully added");
        }
    }




    public User getUser(String type, String first_name, String last_name) throws SQLException {
        User usr;
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
