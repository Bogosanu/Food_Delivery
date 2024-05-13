package dao;

import daoservices.DatabaseConnection;
import model.Order;
import model.OrderProduct;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderProductDao {
    private Connection connection = DatabaseConnection.getConnection();

    public OrderProductDao() throws SQLException {
    }

    public OrderProduct read(int nr, String name) throws SQLException {
        String sql = "SELECT * FROM food_delivery.orderproduct op WHERE op.orderNumber = ? and op.productName = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, nr);
            statement.setString(2, name);
            rs = statement.executeQuery();
            while (rs.next()){
                OrderProduct op = new OrderProduct(rs.getInt("orderNumber"), rs.getString("productName"));
                return op;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }


    public void delete(OrderProduct op) throws SQLException {

        String sql = "DELETE FROM food_delivery.orderproduct op WHERE op.orderNumber = ? AND op.productName = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, op.getOrderNumber());
            statement.setString(2, op.getProductName());
            statement.executeUpdate();
        }

    }

    public void deleteFromOrder(Order o) throws SQLException {

    }

    public void create(Product p, Order o) throws SQLException{
        String sql = "INSERT INTO food_delivery.orderproduct VALUES (?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(2,  o.getNumber());
            statement.setString(1,  p.getName());
            statement.executeUpdate();
        }
    }
}
