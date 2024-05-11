package service;

import daoservices.ProductDaoService;
import daoservices.ProviderDaoService;
import model.Product;
import model.Provider;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;

public class ProviderService {

    private ProviderDaoService databaseService;
    private ProductDaoService databaseproductService;

    public ProviderDaoService getDatabaseService() {
        return databaseService;
    }

    public void setDatabaseService(ProviderDaoService databaseService) {
        this.databaseService = databaseService;
    }

    public ProductDaoService getDatabaseproductService() {
        return databaseproductService;
    }

    public void setDatabaseproductService(ProductDaoService databaseproductService) {
        this.databaseproductService = databaseproductService;
    }

    public ProviderService() throws SQLException {
        this.databaseService = new ProviderDaoService();
    }


    public void create(Scanner scanner) throws SQLException {
        System.out.println("Enter provider name: ");
        String name = scanner.nextLine();
        System.out.println("Enter provider phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter provider address: ");
        String address = scanner.nextLine();
        Provider prov = new Provider(name, phoneNumber, address);
        databaseService.addprovider(prov);
        System.out.println("How many available product types does this provider have?");
        HashSet<Product> products = new HashSet<Product>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < n; ++i)
            products.add(add_product(scanner, name));
        System.out.println("Provider created successfully");
        prov.setAvailableProducts(products);

    }

    public Product add_product(Scanner scanner, String providerName) throws SQLException {
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

        Product p = new Product(name, adultonly, price, weight);
        p.setProviderName(providerName);

        //Provider prov = databaseService.getproviderByName(providerName);
        //prov.addAvailable_product(p);
        databaseproductService.addproduct(p, providerName);
        System.out.println("Product created successfully\n");
        return p;
    }

    public void read(Scanner scanner) throws SQLException {
        System.out.println("Enter provider name");
        String name = scanner.nextLine();
        databaseService.getproviderByName(name);
    }

    public void delete(Scanner scanner) throws SQLException {
        System.out.println("Enter provider name");
        String name = scanner.nextLine();
        Provider prov = databaseService.getproviderByName(name);
        databaseService.removeprovider(name);
    }

    public void update(Scanner scanner) throws SQLException {
        System.out.println("Enter provider name");
        String name = scanner.nextLine();
        Provider prov = databaseService.getproviderByName(name);
        if(prov == null) return;
        System.out.println("Enter new provider phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter new provider address: ");
        String address = scanner.nextLine();

        databaseService.removeprovider(name);
        prov.setPhoneNumber(phoneNumber);
        prov.setAddress(address);
        databaseService.addprovider(prov);
        System.out.println("How many available product types does this provider have?");
        int n = scanner.nextInt();
        scanner.nextLine();
        HashSet<Product> products = new HashSet<Product>();
        for(int i = 0; i < n; ++i)
            products.add(add_product(scanner, name));

        prov.setAvailableProducts(products);
    }




}
