package repository;

import Exceptions.SqlUpdateException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingelton {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "Slon1939net");
            }
        } catch (SQLException e) {
            throw new SqlUpdateException(e.getMessage());
        }
        return connection;
    }
}
