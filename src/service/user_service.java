package service;

import daoservices.userdaoservice;
import model.customer;
import model.driver;
import model.user;

import java.util.Scanner;

public class user_service {
    private userdaoservice databaseService;

    public userdaoservice getDatabaseService() {
        return databaseService;
    }

    public void setDatabaseService(userdaoservice databaseService) {
        this.databaseService = databaseService;
    }

    public user_service(){
        this.databaseService = new userdaoservice();
    }

    public void create(Scanner scanner){
        System.out.println("Enter type of user [customer/driver]:");
        String type = scanner.nextLine().toLowerCase();
        if(!type_valid(type)) { return; }
        userInit(scanner, type);
    }

    public void read(Scanner scanner){
        System.out.println("Enter user first name: ");
        String first_name = scanner.nextLine();
        System.out.println("Enter user last name: ");
        String last_name = scanner.nextLine();
        databaseService.getCustomerByName(first_name, last_name);
        databaseService.getDriverByName(first_name, last_name);
    }

    public void delete(Scanner scanner){
        System.out.println("Enter user first name: ");
        String first_name = scanner.nextLine();
        System.out.println("Enter user last name: ");
        String last_name = scanner.nextLine();
        System.out.println("Enter the user type:");
        String type = scanner.nextLine();
        if(!type_valid(type)) { return; }
        databaseService.removeUser(type, first_name, last_name);
    }

    public void update(Scanner scanner){
        System.out.println("Enter the user type:");
        String type = scanner.nextLine();
        if(!type_valid(type)) { return; }
        System.out.println("Enter user first name: ");
        String first_name = scanner.nextLine();
        System.out.println("Enter user last name: ");
        String last_name = scanner.nextLine();
        user usr = databaseService.getUser(type, first_name, last_name);
        if(usr == null) { return; }

        user usrinfo = setInfo(first_name, last_name, scanner);
        usr.setFirst_name(first_name);
        usr.setLast_name(last_name);
        usr.setPhone_number(usrinfo.getPhone_number());
        if(type.equals("customer")){
            customerInit(scanner, (customer) usr);
        }
        else{
            driverInit(scanner, (driver) usr);
        }

    }

    public boolean type_valid(String type){
        if(!type.equals("customer") && !type.equals("driver")){
            System.out.println("Wrong type");
            return false;
        }
        return true;
    }


    private void userInit(Scanner scanner, String type){
        System.out.println("Enter user first name: ");
        String first_name = scanner.nextLine();
        System.out.println("Enter user last name: ");
        String last_name = scanner.nextLine();

        if (type.equals("customer") && databaseService.getCustomerByName(first_name, last_name) != null) {return;}
        if (type.equals("driver") && databaseService.getDriverByName(first_name, last_name) != null) {return;}

        user usr = setInfo(first_name, last_name, scanner);

        if(type.equals("customer")){
            customer cst = new customer(usr);
            customerInit(scanner, cst);
            usr = cst;
        } else {
            driver drv = new driver(usr);
            driverInit(scanner, drv);
            usr = drv;
        }

        databaseService.addUser(usr);
        System.out.println("Created " + usr);
    }


    private user setInfo(String first_name, String last_name, Scanner scanner){
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();
        return new user(first_name, last_name, phoneNumber);
    }

    private void driverInit(Scanner scanner, driver drv){
        System.out.println("Enter car license plate:");
        String license_plate = scanner.nextLine();
        System.out.println("Enter car model:");
        String car_model = scanner.nextLine();

        drv.setCar_model(car_model);
        drv.setLicense_plate(license_plate);

    }

    private void customerInit(Scanner scanner, customer cst){
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
