package service;

import daoservices.productdaoservice;
import daoservices.providerdaoservice;

import java.util.Scanner;

public class provider_service {

    private providerdaoservice databaseService;
    private productdaoservice databaseproductService;

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
        int n = scanner.nextInt();
        scanner.nextLine();

        add_product(scanner);

    }

    public void add_product(Scanner scanner){
        System.out.println("Would you like to add a product?");
    }
}
