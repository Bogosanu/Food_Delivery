package dao;

import daoservices.DatabaseConnection;
import model.Driver;
import model.User;
import java.sql.*;

import java.util.ArrayList;

public class DriverDao {

    //private static ArrayList<Driver> drivers = new ArrayList<>();
    private Connection connection = DatabaseConnection.getConnection();

    public DriverDao() throws SQLException {
    }



    public Driver read(String first_name, String last_name) throws SQLException {
        String sql = "SELECT * FROM driver d WHERE d.firstName = ? AND d.lastName = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            rs = statement.executeQuery();

            while (rs.next()){
                User u = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("phoneNumber"));
                Driver d = new Driver(u);
                d.setLicensePlate(rs.getString("licensePlate"));
                d.setCarModel(rs.getString("carModel"));
                return  d;
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

    public void delete(Driver d) throws SQLException {
        String sql = "DELETE FROM driver d WHERE d.firstName = ? AND d.lastName = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, d.getFirstName());
            statement.setString(2, d.getLastName());
            statement.executeUpdate();
        }
    }

    public void create(Driver d) throws SQLException{
        String sql = "INSERT INTO DRIVER VALUES (?, ?, ?, ?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1,  d.getPhoneNumber());
            statement.setString(2, d.getFirstName());
            statement.setString(3, d.getLastName());
            statement.setString(4, d.getLicensePlate());
            statement.setString(5, d.getCarModel());
            statement.executeUpdate();
        }
    }
}
