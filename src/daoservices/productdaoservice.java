package daoservices;

import dao.productdao;
import model.product;

public class productdaoservice {
    private productdao productDao;

    public productdaoservice() {
        this.productDao = new productdao();
    }
    public product getproductByName(String name){
        product p = productDao.read(name);
        if(p != null){
            System.out.println(p);
        }
        else{
            System.out.println("No product with this name");
        }
        return p;
    }

    public void removeproduct(String name){
        product p = getproductByName(name);
        if(p == null) return;
        productDao.delete(p);
        System.out.println("Removed product " + name);
    }

    public void addproduct(product p){
        if(p != null){
            productDao.create(p);
            System.out.println("Product successfully added");
        }
    }
}
