package daoservices;

import dao.ProductDao;
import model.Product;

import java.sql.SQLException;

public class ProductDaoService {
    private ProductDao productDao;

    public ProductDaoService() throws SQLException {
        this.productDao = new ProductDao();
    }
    public Product getproductByName(String name, String providerName) throws SQLException {
        Product p = productDao.read(name, providerName);
        if(p != null){
            System.out.println(p);
        }
        else{
            System.out.println("No product with this name");
        }
        return p;
    }

    public void removeproduct(String name, String providerName) throws SQLException {
        Product p = getproductByName(name, providerName);
        if(p == null) return;
        productDao.delete(p);
        System.out.println("Removed product " + name);
    }

    public void addproduct(Product p, String providerName) throws SQLException {
        if(p != null){
            productDao.create(p, providerName);
            System.out.println("Product successfully added");
        }
    }
}
