package service;

import daoservices.UserDaoService;
import model.Customer;
import model.Driver;
import model.User;
import misc.FileManagement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    private UserDaoService databaseService;

    public UserDaoService getDatabaseService() {
        return databaseService;
    }

    public void setDatabaseService(UserDaoService databaseService) {
        this.databaseService = databaseService;
    }

    public UserService() throws SQLException {
        this.databaseService = new UserDaoService();
    }

    public void create(Scanner scanner) throws SQLException {
        System.out.println("Enter type of user [customer/driver]:");
        String type = scanner.nextLine().toLowerCase();
        if(!type_valid(type)) { return; }
        userInit(scanner, type);
    }

    public void read(Scanner scanner) throws SQLException {
        System.out.println("Enter user first name: ");
        String first_name = scanner.nextLine();
        System.out.println("Enter user last name: ");
        String last_name = scanner.nextLine();
        if(databaseService.getCustomerByName(first_name, last_name) != null || databaseService.getDriverByName(first_name, last_name) != null)
            FileManagement.scriereFisierChar("audit.csv", "read user " + first_name + " " + last_name);
    }

    public void delete(Scanner scanner) throws SQLException {
        System.out.println("Enter user first name: ");
        String first_name = scanner.nextLine();
        System.out.println("Enter user last name: ");
        String last_name = scanner.nextLine();
        System.out.println("Enter the user type:");
        String type = scanner.nextLine();
        if(!type_valid(type)) { return; }
        User usr = databaseService.getUser(type, first_name, last_name);
        if(usr == null) { return; }
        FileManagement.scriereFisierChar("audit.csv", "delete user and all their orders" + first_name + " " + last_name);
        databaseService.removeUser(type, first_name, last_name);
    }

    public void update(Scanner scanner) throws SQLException {
        System.out.println("Enter the user type:");
        String type = scanner.nextLine();
        if(!type_valid(type)) { return; }
        System.out.println("Enter user first name: ");
        String first_name = scanner.nextLine();
        System.out.println("Enter user last name: ");
        String last_name = scanner.nextLine();
        User usr = databaseService.getUser(type, first_name, last_name);
        if(usr == null) { return; }
        FileManagement.scriereFisierChar("audit.csv", "update user " + first_name + " " + last_name);
        User usrinfo = setInfo(first_name, last_name, scanner);
        usr.setFirstName(first_name);
        usr.setLastName(last_name);
        usr.setPhoneNumber(usrinfo.getPhoneNumber());
        if(type.equals("customer")){
            customerInit(scanner, (Customer) usr);
            databaseService.removeUser(type, first_name, last_name);
            databaseService.addUser(usr);
        }
        else{
            driverInit(scanner, (Driver) usr);
            databaseService.removeUser(type, first_name, last_name);
            databaseService.addUser(usr);
        }

    }

    public boolean type_valid(String type){
        if(!type.equals("customer") && !type.equals("driver")){
            System.out.println("Wrong type");
            return false;
        }
        return true;
    }


    private void userInit(Scanner scanner, String type) throws SQLException {
        System.out.println("Enter user first name: ");
        String first_name = scanner.nextLine();
        System.out.println("Enter user last name: ");
        String last_name = scanner.nextLine();

        FileManagement.scriereFisierChar("audit.csv", "create user " + first_name + " " + last_name);

        if (type.equals("customer") && databaseService.getCustomerByName(first_name, last_name) != null) {return;}
        if (type.equals("driver") && databaseService.getDriverByName(first_name, last_name) != null) {return;}

        User usr = setInfo(first_name, last_name, scanner);

        if(type.equals("customer")){
            Customer cst = new Customer(usr);
            customerInit(scanner, cst);
            usr = cst;
        } else {
            Driver drv = new Driver(usr);
            driverInit(scanner, drv);
            usr = drv;
        }

        databaseService.addUser(usr);
        System.out.println("Created " + usr);
    }


    private User setInfo(String first_name, String last_name, Scanner scanner){
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();
        return new User(first_name, last_name, phoneNumber);
    }

    private void driverInit(Scanner scanner, Driver drv){
        System.out.println("Enter car license plate:");
        String license_plate = scanner.nextLine();
        System.out.println("Enter car model:");
        String car_model = scanner.nextLine();

        drv.setCarModel(car_model);
        drv.setLicensePlate(license_plate);


    }

    private void customerInit(Scanner scanner, Customer cst){
        System.out.println("Enter your address:");
        String address = scanner.nextLine();
        System.out.println("Are you an adult?[yes/no]");
        String adult = scanner.nextLine();
        if(adult.toLowerCase().equals("yes")){
            cst.setAdult(true);
        }
        else{
            cst.setAdult(false);
        }
        cst.setAddress(address);


    }
}
