package service;

import daoservices.*;
import daoservices.OrderDaoService;
import misc.FileManagement;
import model.*;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class OrderService {
    
    private OrderDaoService databaseService;


    private OrderProductDaoService dbOrdProdService;
    private UserDaoService dbuserService;

    private ProviderDaoService dbprvService;

    private ProductDaoService dbproService;

    public OrderDaoService getDatabaseService() {
        return databaseService;
    }

    public void setDbOrdProdService(OrderProductDaoService dbOrdProdService) {
        this.dbOrdProdService = dbOrdProdService;
    }

    public void setDatabaseService(OrderDaoService databaseService) {
        this.databaseService = databaseService;
    }

    public UserDaoService getDbuserService() {
        return dbuserService;
    }

    public void setDbuserService(UserDaoService dbuserService) {
        this.dbuserService = dbuserService;
    }

    public ProviderDaoService getDbprvService() {
        return dbprvService;
    }

    public void setDbprvService(ProviderDaoService dbprvService) {
        this.dbprvService = dbprvService;
    }

    public ProductDaoService getDbproService() {
        return dbproService;
    }

    public void setDbproService(ProductDaoService dbproService) {
        this.dbproService = dbproService;
    }

    public OrderService() throws SQLException {
        this.databaseService = new OrderDaoService();
    }

    public void create(Scanner scanner) throws SQLException {
        System.out.println("Enter customer first name: ");
        String cst_first_name = scanner.nextLine();
        System.out.println("Enter customer last name: ");
        String cst_last_name = scanner.nextLine();

        Customer c = dbuserService.getCustomerByName(cst_first_name, cst_last_name);
        if(c == null) return;

        System.out.println("Enter driver first name: ");
        String drv_first_name = scanner.nextLine();
        System.out.println("Enter driver last name: ");
        String drv_last_name = scanner.nextLine();

        Driver d = dbuserService.getDriverByName(drv_first_name, drv_last_name);
        if(d == null) return;

        System.out.println("Enter provider name: ");
        String prv_name = scanner.nextLine();

        Provider prv = dbprvService.getproviderByName(prv_name);
        if(prv == null) return;

        System.out.println("Enter product count: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Product> order_products = new ArrayList<Product>();
        for(int i = 0; i < n; ++i){
            System.out.println("Product name: ");
            String pro_name = scanner.nextLine();
            Product pro = dbproService.getproductByName(pro_name, prv_name);
            /*boolean in_provider_list = false;
            for(Product p : prv.getAvailableProducts()){
                if(p.getName().equals(pro.getName()))
                    in_provider_list = true;
            }
            if(!in_provider_list){
                System.out.println("Product not found in provider's available products");
                i--;
                continue;
            }*/
            if(pro != null) {
                if (!c.isAdult() && pro.isAdultsOnly()) {
                    System.out.println("Customer is not allowed by law to buy this product");
                    i--;
                    continue;
                }
                order_products.add(pro);
            }
            else{
                i--;
            }
        }



        System.out.println("Enter order number");
        int nr = scanner.nextInt();
        scanner.nextLine();
        FileManagement.scriereFisierChar("audit.csv", "create order " + nr);
        Order ord = new Order(c, d, prv, nr);
        databaseService.addOrder(ord);
        for(Product prd : order_products)
            dbOrdProdService.addOrderProduct(prd, ord);
        System.out.println("Order created successfully");
    }

    public void read(Scanner scanner) throws SQLException {
        System.out.println("Enter order number");
        int nr = scanner.nextInt();
        scanner.nextLine();
        if(databaseService.getOrderByNumber(nr) != null)
            FileManagement.scriereFisierChar("audit.csv", "read order " + nr);
    }

    public void delete(Scanner scanner) throws SQLException {
        System.out.println("Enter order number");
        int nr = scanner.nextInt();
        scanner.nextLine();
        if(databaseService.getOrderByNumber(nr) == null)
            return;
        FileManagement.scriereFisierChar("audit.csv", "delete order " + nr);
        databaseService.removeOrder(nr);
    }

}
