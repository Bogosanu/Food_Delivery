package dao;

import daoservices.DatabaseConnection;
import model.Product;
import model.Provider;
import java.sql.*;

import java.util.ArrayList;

public class ProviderDao {

   // private static ArrayList<Provider> Providers = new ArrayList<>();

    private static ProviderDao providerDao;
    private Connection connection = DatabaseConnection.getConnection();

    private ProviderDao() throws SQLException {
    }

    public static ProviderDao getInstance() throws SQLException {
        if(providerDao == null){
            providerDao = new ProviderDao();
        }
        return providerDao;
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

        String sql4 = "DELETE op FROM orderproduct op JOIN food_delivery.order o ON o.number = op.orderNumber WHERE o.providerName = ?";

        try(PreparedStatement statement4 = connection.prepareStatement(sql4);) {
            statement4.setString(1, p.getName());
            statement4.executeUpdate();
        }



        String sql3 = "DELETE FROM food_delivery.order o WHERE o.providerName = ?";
        try(PreparedStatement statement3 = connection.prepareStatement(sql3);) {
            statement3.setString(1, p.getName());
            statement3.executeUpdate();
        }


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
