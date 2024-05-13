
import daoservices.OrderProductDaoService;
import service.OrderService;
import service.ProductService;
import service.ProviderService;
import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService;

    static {
        try {
            userService = new UserService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ProductService productService;

    static {
        try {
            productService = new ProductService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ProviderService providerService;

    static {
        try {
            providerService = new ProviderService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static OrderService orderService;

    static {
        try {
            orderService = new OrderService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {

        OrderProductDaoService dbOrdProdService = new OrderProductDaoService();

        providerService.setDatabaseproductService(productService.getDatabaseService());
        orderService.setDbproService(productService.getDatabaseService());
        orderService.setDbprvService(providerService.getDatabaseService());
        orderService.setDbuserService(userService.getDatabaseService());
        orderService.setDbOrdProdService(dbOrdProdService);

        mainMenu();
    }

    private static void mainMenu() throws SQLException {
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Users");
            System.out.println("2. Providers");
            System.out.println("3. Products");
            System.out.println("4. Orders");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userMenu();
                    break;
                case "2":
                    providerMenu();
                    break;
                case "3":
                    productMenu();
                    break;
                case "4":
                    orderMenu();
                    break;
                case "5":
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void userMenu() throws SQLException {
        String choice;
        do {
            System.out.println("\n--- User Operations ---");
            System.out.println("1. Create User");
            System.out.println("2. Read User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userService.create(scanner);
                    break;
                case "2":
                    userService.read(scanner);
                    break;
                case "3":
                    userService.update(scanner);
                    break;
                case "4":
                    userService.delete(scanner);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please choose a valid operation or 5 to go back.");
            }
        } while (!choice.equals("5"));
    }

    private static void productMenu() throws SQLException {
        String choice;
        do {
            System.out.println("\n--- Product Operations ---");
            System.out.println("1. Read Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    productService.read(scanner);
                    break;
                case "2":
                    productService.update(scanner);
                    break;
                case "3":
                    productService.delete(scanner);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice. Please choose a valid operation or 4 to go back.");
            }
        } while (!choice.equals("4"));
    }

    private static void providerMenu() throws SQLException {
        String choice;
        do {
            System.out.println("\n--- Provider Operations ---");
            System.out.println("1. Create Provider");
            System.out.println("2. Read Provider");
            System.out.println("3. Update Provider");
            System.out.println("4. Delete Provider");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    providerService.create(scanner);
                    break;
                case "2":
                    providerService.read(scanner);
                    break;
                case "3":
                    providerService.update(scanner);
                    break;
                case "4":
                    providerService.delete(scanner);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please choose a valid operation or 5 to go back.");
            }
        } while (!choice.equals("5"));
    }

    private static void orderMenu() throws SQLException {
        String choice;
        do {
            System.out.println("\n--- Order Operations ---");
            System.out.println("1. Create Order");
            System.out.println("2. Read Order");
            System.out.println("3. Delete Order");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    orderService.create(scanner);
                    break;
                case "2":
                    orderService.read(scanner);
                    break;
                case "3":
                    orderService.delete(scanner);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice. Please choose a valid operation or 4 to go back.");
            }
        } while (!choice.equals("4"));
    }

}