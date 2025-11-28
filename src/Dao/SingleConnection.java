package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {

    private static Connection instance = null;
    private SingleConnection() {
        try {
            String url = "jdbc:mysql://localhost:8889/siliconmap?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "root";

            instance = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion à la base de données établie !");
            
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }
    public static Connection getInstance() {
        if (instance == null) {
            new SingleConnection();
        }
        return instance;
    }
    public static void close() {
        if (instance != null) {
            try {
                instance.close();
                instance = null;
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture : " + e.getMessage());
            }
        }
    }
}