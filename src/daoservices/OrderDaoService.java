package daoservices;

import dao.OrderDao;
import dao.OrderProductDao;
import model.Order;

import java.sql.SQLException;

public class OrderDaoService {

    private OrderDao orderDao = OrderDao.getInstance();
    private OrderProductDao orderProductDao = OrderProductDao.getInstance();
    public OrderDaoService() throws SQLException {
    }
    public Order getOrderByNumber(int nr) throws SQLException {
        Order o = orderDao.read(nr);
        if(o != null) {
            System.out.println("Order number: " + o.getNumber());
            System.out.println("Customer: " + o.getCustomer().getFirstName() + " " + o.getCustomer().getLastName());
            System.out.println("Driver: " + o.getDriver().getFirstName() + " " + o.getDriver().getLastName());
            System.out.println("Provided by: " + o.getProvider().getName());
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
