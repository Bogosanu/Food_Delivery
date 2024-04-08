package daoservices;

import dao.orderdao;
import model.order;

public class orderdaoservice {

    private orderdao orderDao;

    public orderdaoservice() {
        this.orderDao = new orderdao();
    }
    public order getOrderByNumber(int nr){
        order o = orderDao.read(nr);
        if(o != null){
            System.out.println(o);
        }
        else{
            System.out.println("No order with this number");
        }
        return o;
    }

    public void removeOrder(int nr){
        order o = getOrderByNumber(nr);
        if(o == null) return;
        orderDao.delete(o);
        System.out.println("Removed order " + nr);
    }

    public void addOrder(order o){
        if(o != null){
            orderDao.create(o);
            System.out.println("Order successfully added");
        }
    }
}
