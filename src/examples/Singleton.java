package examples;

import static examples.Interface.dateBaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Singleton {

    private static Singleton instance = null;

    Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public ResultSet executeSelect(String select) throws SQLException {
        Connection connection = dateBaseConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(select);
        return result;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Connection connection = dateBaseConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        return result;
    }

    public int executeUpdate(String query) throws SQLException {
        int result = 0;
        try {
            Connection connection = dateBaseConnection();
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid arguments");
        }
        return result;
    }

    public int executeInsert(String query) throws SQLException {
        int result = 0;
        try {
            Connection connection = dateBaseConnection();
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid arguments");
        }
        return result;
    }

    public int executeDelete(String delete) throws SQLException {
        int result = 0;
        try {
            Connection connection = dateBaseConnection();
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(delete);
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Can't delete this one. Integrity constraint violation");
        } catch (Exception e) {
        }
        return result;
    }
}
