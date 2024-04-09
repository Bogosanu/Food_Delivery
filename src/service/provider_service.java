package service;

import daoservices.productdaoservice;
import daoservices.providerdaoservice;
import model.product;
import model.provider;

import java.util.HashSet;
import java.util.Scanner;

public class provider_service {

    private providerdaoservice databaseService;
    private productdaoservice databaseproductService;

    public providerdaoservice getDatabaseService() {
        return databaseService;
    }

    public void setDatabaseService(providerdaoservice databaseService) {
        this.databaseService = databaseService;
    }

    public productdaoservice getDatabaseproductService() {
        return databaseproductService;
    }

    public void setDatabaseproductService(productdaoservice databaseproductService) {
        this.databaseproductService = databaseproductService;
    }

    public provider_service(){
        this.databaseService = new providerdaoservice();
    }


    public void create(Scanner scanner){
        System.out.println("Enter provider name: ");
        String name = scanner.nextLine();
        System.out.println("Enter provider phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter provider address: ");
        String address = scanner.nextLine();
        System.out.println("How many available product types does this provider have?");
        HashSet<product> products = new HashSet<product>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < n; ++i)
            products.add(add_product(scanner));

        provider prov = new provider(name, phoneNumber, address, products);
        databaseService.addprovider(prov);
        System.out.println("Provider created successfully");

    }

    public product add_product(Scanner scanner){
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
        databaseproductService.addproduct(p);
        System.out.println("Product created successfully\n");
        return p;
    }

    public void read(Scanner scanner){
        System.out.println("Enter provider name");
        String name = scanner.nextLine();
        databaseService.getproviderByName(name);
    }

    public void delete(Scanner scanner){
        System.out.println("Enter provider name");
        String name = scanner.nextLine();
        provider prov = databaseService.getproviderByName(name);
        for(product p : prov.getAvailable_products())
            databaseproductService.removeproduct(p.getName()); //stergem intai toate produsele
        databaseService.removeprovider(name);
    }

    public void update(Scanner scanner){
        System.out.println("Enter provider name");
        String name = scanner.nextLine();
        provider prov = databaseService.getproviderByName(name);
        if(prov == null) return;
        System.out.println("Enter new provider phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter new provider address: ");
        String address = scanner.nextLine();

        System.out.println("How many available product types does this provider have?");
        int n = scanner.nextInt();
        scanner.nextLine();

        for(product p : prov.getAvailable_products())
            databaseproductService.removeproduct(p.getName());  //stergem produsele vechi din baza de date

        HashSet<product> products = new HashSet<product>();
        for(int i = 0; i < n; ++i)
            products.add(add_product(scanner));
        prov.setPhone_number(phoneNumber);
        prov.setAddress(address);
        prov.setAvailable_products(products);

    }




}
