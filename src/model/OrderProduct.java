package model;

public class OrderProduct {

    private int orderNumber;

    private String productName;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public OrderProduct(int orderNumber, String productName) {
        this.orderNumber = orderNumber;
        this.productName = productName;
    }
}
