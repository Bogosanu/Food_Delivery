package dao;
import daoservices.DatabaseConnection;
import model.Customer;
import model.Driver;
import model.User;
import java.sql.*;

import java.util.ArrayList;

public class CustomerDao {

    //private static ArrayList<Customer> customers = new ArrayList<>();
    private static CustomerDao customerDao;
    private Connection connection = DatabaseConnection.getConnection();

    private CustomerDao() throws SQLException {
    }

    public static CustomerDao getInstance() throws SQLException {
        if(customerDao == null){
            customerDao = new CustomerDao();
        }
        return customerDao;
    }

    public Customer read(String first_name, String last_name) throws SQLException {
        String sql = "SELECT * FROM customer c WHERE c.firstName = ? AND c.lastName = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            rs = statement.executeQuery();

            while (rs.next()){
                User u = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("phoneNumber"));
                model.Customer c = new Customer(u);
                c.setAdult(rs.getBoolean("adult"));
                c.setAddress(rs.getString("address"));
                return c;
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





    public void delete(Customer c) throws SQLException {

        String sql3 = "DELETE op FROM orderproduct op JOIN food_delivery.order o ON o.number = op.orderNumber WHERE o.cstFirstName = ? AND o.cstLastName = ?";

        try(PreparedStatement statement3 = connection.prepareStatement(sql3);) {
            statement3.setString(1, c.getFirstName());
            statement3.setString(2, c.getLastName());
            statement3.executeUpdate();
        }


        String sql2 = "DELETE FROM food_delivery.order o WHERE o.cstFirstName = ? AND o.cstLastName = ?";

        try(PreparedStatement statement2 = connection.prepareStatement(sql2);) {
            statement2.setString(1, c.getFirstName());
            statement2.setString(2, c.getLastName());
            statement2.executeUpdate();
        }

        String sql = "DELETE FROM customer c WHERE c.firstName = ? AND c.lastName = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, c.getFirstName());
            statement.setString(2, c.getLastName());
            statement.executeUpdate();
        }
    }

    public void create(Customer c) throws SQLException {
        String sql = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, c.getPhoneNumber());
            statement.setString(2, c.getFirstName());
            statement.setString(3, c.getLastName());
            statement.setString(4, c.getAddress());
            statement.setBoolean(5, c.isAdult());
            statement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Primary Key violation occurred: " + e.getMessage());
            } else {
                throw e;
            }
        }
    }

}
