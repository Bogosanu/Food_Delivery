package daoservices;

import dao.OrderDao;
import model.Order;

import java.sql.SQLException;

public class OrderDaoService {

    private OrderDao orderDao;
    public OrderDaoService() throws SQLException {
        this.orderDao = new OrderDao();
    }
    public Order getOrderByNumber(int nr) throws SQLException {
        Order o = orderDao.read(nr);
        if(o != null){
            System.out.println(o);
        }
        else{
            System.out.println("No order with this number");
        }
        return o;
    }

    public void removeOrder(int nr) throws SQLException {
        Order o = getOrderByNumber(nr);
        if(o == null) return;
        orderDao.delete(o);
        System.out.println("Removed order " + nr);
    }

    public void addOrder(Order o) throws SQLException {
        if(o != null){
            orderDao.create(o);
            System.out.println("Order successfully added");
        }
    }
}
