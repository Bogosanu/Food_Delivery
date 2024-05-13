package daoservices;

import dao.OrderDao;
import dao.OrderProductDao;
import model.Order;
import model.OrderProduct;
import model.Product;

import java.sql.SQLException;

public class OrderProductDaoService {

    private OrderProductDao orderProductDao;

    public OrderProductDaoService() throws SQLException {
        this.orderProductDao = new OrderProductDao();
    }


    public OrderProduct getOrderProduct(Product p, Order o) throws SQLException {
        OrderProduct op = orderProductDao.read(o.getNumber(), p.getName());
        if(op == null)
            System.out.println("No such product in that order");
        return op;
    }

    public void removeOrderProduct(Product p, Order o) throws SQLException {
        OrderProduct op = getOrderProduct(p, o);
        if(op == null)
            return;
        orderProductDao.delete(op);
    }




    public void addOrderProduct(Product p, Order o) throws SQLException {
        if(o != null && p != null){
            orderProductDao.create(p, o);
        }
    }
}
