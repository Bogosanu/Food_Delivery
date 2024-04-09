package service;

import daoservices.productdaoservice;
import model.product;

import java.util.Scanner;

public class product_service {
    private productdaoservice databaseService;

    public productdaoservice getDatabaseService() {
        return databaseService;
    }

    public void setDatabaseService(productdaoservice databaseService) {
        this.databaseService = databaseService;
    }

    public product_service(){
        this.databaseService = new productdaoservice();
    }

    public void create(Scanner scanner) {
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product price: ");
        float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Enter product weight(grams): ");
        int weight = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Is this product for adults only?[yes/no]: ");
        String adult = scanner.nextLine();
        boolean adultonly;
        if (adult.toLowerCase().equals("yes")) {
            adultonly = true;
        } else {
            adultonly = false;
        }

        product p = new product(name, adultonly, price, weight);
        databaseService.addproduct(p);
        System.out.println("Product created successfully");
    }

    public void read(Scanner scanner){
        System.out.println("Enter product name");
        String name = scanner.nextLine();
        databaseService.getproductByName(name);
    }

    public void delete(Scanner scanner){
        System.out.println("Enter product name");
        String name = scanner.nextLine();
        databaseService.removeproduct(name);

    }

    public void update(Scanner scanner){
        System.out.println("Enter product name");
        String name = scanner.nextLine();
        product p = databaseService.getproductByName(name);
        if(p == null) return;

        System.out.println("Enter new product price: ");
        float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Enter new product weight(grams): ");
        int weight = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Is this product for adults only?[yes/no]: ");
        String adult = scanner.nextLine();
        boolean adultonly;
        if (adult.toLowerCase().equals("yes")) {
            adultonly = true;
        } else {
            adultonly = false;
        }
        p.setName(name);
        p.setAdultsonly(adultonly);
        p.setPrice(price);
        p.setWeight(weight);
    }

}
