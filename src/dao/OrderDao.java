package dao;

import daoservices.DatabaseConnection;
import model.Order;
import model.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import daoservices.UserDaoService;

public class OrderDao {

    //private static ArrayList<Order> Orders = new ArrayList<>();

    private Connection connection = DatabaseConnection.getConnection();

    public OrderDao() throws SQLException {
    }

    public Order read(int nr) throws SQLException {
        String sql = "SELECT * FROM order o WHERE o.number = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, nr);
            rs = statement.executeQuery();
            while (rs.next()){
                model.Order o = new Order(rs.getInt("number"));
                return o;
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

    public void delete(Order o) throws SQLException {

        String sql = "DELETE FROM order o WHERE o.number = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, o.getNumber());
            statement.executeUpdate();
        }

    }

    public void create(Order o) throws SQLException{
        String sql = "INSERT INTO PROVIDER VALUES (?, ?, ?, ?, ?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1,  o.getNumber());
            statement.setString(2,  o.getCustomer().getFirstName());
            statement.setString(3,  o.getCustomer().getLastName());
            statement.setString(4,  o.getDriver().getFirstName());
            statement.setString(5,  o.getDriver().getLastName());
            statement.setString(6,  o.getProvider().getName());
            statement.executeUpdate();
        }
    }
}
