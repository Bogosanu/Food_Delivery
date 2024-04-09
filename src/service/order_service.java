package service;

import daoservices.*;
import daoservices.orderdaoservice;
import model.*;

import java.util.Scanner;
import java.util.ArrayList;

public class order_service {
    
    private orderdaoservice databaseService;
    private userdaoservice dbuserService;

    private providerdaoservice dbprvService;

    private productdaoservice dbproService;

    public orderdaoservice getDatabaseService() {
        return databaseService;
    }

    public void setDatabaseService(orderdaoservice databaseService) {
        this.databaseService = databaseService;
    }

    public userdaoservice getDbuserService() {
        return dbuserService;
    }

    public void setDbuserService(userdaoservice dbuserService) {
        this.dbuserService = dbuserService;
    }

    public providerdaoservice getDbprvService() {
        return dbprvService;
    }

    public void setDbprvService(providerdaoservice dbprvService) {
        this.dbprvService = dbprvService;
    }

    public productdaoservice getDbproService() {
        return dbproService;
    }

    public void setDbproService(productdaoservice dbproService) {
        this.dbproService = dbproService;
    }

    public order_service(){
        this.databaseService = new orderdaoservice();
    }

    public void create(Scanner scanner) {
        System.out.println("Enter customer first name: ");
        String cst_first_name = scanner.nextLine();
        System.out.println("Enter customer last name: ");
        String cst_last_name = scanner.nextLine();

        customer c = dbuserService.getCustomerByName(cst_first_name, cst_last_name);
        if(c == null) return;

        System.out.println("Enter driver first name: ");
        String drv_first_name = scanner.nextLine();
        System.out.println("Enter driver last name: ");
        String drv_last_name = scanner.nextLine();

        driver d = dbuserService.getDriverByName(drv_first_name, drv_last_name);
        if(d == null) return;

        System.out.println("Enter provider name: ");
        String prv_name = scanner.nextLine();

        provider prv = dbprvService.getproviderByName(prv_name);
        if(prv == null) return;

        System.out.println("Enter product count: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        ArrayList<product> order_products = new ArrayList<product>();
        for(int i = 0; i < n; ++i){
            System.out.println("Product name: ");
            String pro_name = scanner.nextLine();
            product pro = dbproService.getproductByName(pro_name);
            boolean in_provider_list = false;
            for(product p : prv.getAvailable_products()){
                if(p.getName().equals(pro.getName()))
                    in_provider_list = true;
            }
            if(!in_provider_list){
                System.out.println("Product not found in provider's available products");
                i--;
                continue;
            }
            if(!c.isAdult() && pro.isAdultsonly()){
                System.out.println("Customer is not allowed by law to buy this product");
                i--;
                continue;
            }
            order_products.add(pro);
        }


        System.out.println("Enter order number");
        int nr = scanner.nextInt();
        scanner.nextLine();

        order ord = new order(c, d, prv, order_products, nr);
        databaseService.addOrder(ord);
        System.out.println("Order created successfully");
    }

    public void read(Scanner scanner){
        System.out.println("Enter order number");
        int nr = scanner.nextInt();
        scanner.nextLine();
        databaseService.getOrderByNumber(nr);
    }

    public void delete(Scanner scanner){
        System.out.println("Enter order number");
        int nr = scanner.nextInt();
        scanner.nextLine();
        databaseService.removeOrder(nr);

    }

    //public void update(Scanner scanner){}
}
