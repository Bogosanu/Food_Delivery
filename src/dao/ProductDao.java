package dao;

import daoservices.DatabaseConnection;
import model.Driver;
import model.Product;
import model.User;

import java.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao {
    //private static ArrayList<Product> Products = new ArrayList<>();

    private Connection connection = DatabaseConnection.getConnection();

    public ProductDao() throws SQLException {
    }

    public Product read(String name, String providerName) throws SQLException {
        String sql = "SELECT * FROM product p WHERE p.name = ? AND p.providerName = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            statement.setString(2, providerName);
            rs = statement.executeQuery();

            while (rs.next()){
                model.Product p = new Product(rs.getString("name"), rs.getBoolean("adultsOnly"), rs.getFloat("price"), rs.getInt("weight"));
                return p;
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

    public void delete(Product p) throws SQLException {
        String sql = "DELETE FROM product p WHERE p.name = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, p.getName());
            statement.executeUpdate();
        }
    }

    public void create(Product p, String providerName) throws SQLException{
        String sql = "INSERT INTO PRODUCT VALUES (?, ?, ?, ?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1,  p.getName());
            statement.setBoolean(2, p.isAdultsOnly());
            statement.setFloat(3, p.getPrice());
            statement.setInt(4, p.getWeight());
            statement.setString(5, providerName);
            statement.executeUpdate();
        }
    }
}
