package service;

import daoservices.ProductDaoService;
import misc.FileManagement;
import model.Product;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductService {
    private ProductDaoService databaseService;

    public ProductDaoService getDatabaseService() {
        return databaseService;
    }

    public void setDatabaseService(ProductDaoService databaseService) {
        this.databaseService = databaseService;
    }

    public ProductService() throws SQLException {
        this.databaseService = new ProductDaoService();
    }

    /*public void create(Scanner scanner) {
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
        databaseService.addproduct(p);
        System.out.println("Product created successfully");
    }*/

    public void read(Scanner scanner) throws SQLException {
        System.out.println("Enter product name");
        String name = scanner.nextLine();
        System.out.println("Enter product provider name");
        String providerName = scanner.nextLine();
        if(databaseService.getproductByName(name, providerName) != null)
            FileManagement.scriereFisierChar("audit.csv", "read product " + name + " by " + providerName);
    }

    public void delete(Scanner scanner) throws SQLException {
        System.out.println("Enter product name");
        String name = scanner.nextLine();
        System.out.println("Enter product provider name");
        String providerName = scanner.nextLine();
        if(databaseService.getproductByName(name, providerName) == null) return;
        FileManagement.scriereFisierChar("audit.csv", "delete product " + name + " by " + providerName);
        databaseService.removeproduct(name, providerName);
    }

    public void update(Scanner scanner) throws SQLException {
        System.out.println("Enter product name");
        String name = scanner.nextLine();
        System.out.println("Enter product provider name");
        String providerName = scanner.nextLine();
        Product p = databaseService.getproductByName(name, providerName);
        if(p == null) return;

        FileManagement.scriereFisierChar("audit.csv", "update product " + name + " by " + providerName);
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
        p.setAdultsOnly(adultonly);
        p.setPrice(price);
        p.setWeight(weight);
        databaseService.removeproduct(name, providerName);
        databaseService.addproduct(p, providerName);
    }

}
