package dao;

import daoservices.DatabaseConnection;
import model.Product;
import model.Provider;
import java.sql.*;

import java.util.ArrayList;

public class ProviderDao {

   // private static ArrayList<Provider> Providers = new ArrayList<>();
   private Connection connection = DatabaseConnection.getConnection();

    public ProviderDao() throws SQLException {
    }

    public Provider read(String name) throws SQLException {
        String sql = "SELECT * FROM provider p WHERE p.name = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            rs = statement.executeQuery();
            while (rs.next()){
                model.Provider p = new Provider(rs.getString("name"), rs.getString("phoneNumber"), rs.getString("address"));
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

    public void delete(Provider p) throws SQLException {
        String sql2 = "DELETE FROM product p WHERE p.providerName = ?";

        try(PreparedStatement statement2 = connection.prepareStatement(sql2);) {
            statement2.setString(1, p.getName());
            statement2.executeUpdate();
        }


        String sql = "DELETE FROM provider p WHERE p.name = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, p.getName());
            statement.executeUpdate();
        }

    }

    public void create(Provider p) throws SQLException{
        String sql = "INSERT INTO PROVIDER VALUES (?, ?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1,  p.getName());
            statement.setString(2,  p.getPhoneNumber());
            statement.setString(3,  p.getAddress());
            statement.executeUpdate();
        }
    }
}
