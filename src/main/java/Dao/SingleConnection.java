package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {

    private static Connection connection = null;
    private SingleConnection() {
        try {
            String url = "jdbc:mysql://localhost:8889/siliconmap?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "root";

            connection = DriverManager.getConnection(url, user, password);
            
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }
    }
    public static Connection GetConnection() {
        if (connection == null) {
            new SingleConnection();
        }
        return connection;
    }
    public static void close() {
        if (connection != null) {
            try {
            	connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
            	throw new RuntimeException(e);
            }
        }
    }
}