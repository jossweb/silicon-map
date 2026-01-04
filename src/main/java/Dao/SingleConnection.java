package Dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;


public class SingleConnection {

    private static Connection connection = null;
    public SingleConnection() {

        HashMap<String, String> dbsInfosMap = new HashMap<String, String>();

        Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .systemProperties()
            .load();

        for (DotenvEntry e : dotenv.entries()) {
            dbsInfosMap.put(e.getKey(), e.getValue());
        }

        String host;
        String port;
        String dbName;
        String dbUser;
        String dbPassword;
        String ssl;

        String selectedDb = System.getProperty("SELECTED_DB");
        if (selectedDb == "REMOTE"){
            host = dbsInfosMap.get("REMOTEHOSTNAME");
            port = dbsInfosMap.get("REMOTEPORT");
            dbName = dbsInfosMap.get("REMOTEDBNAME");
            dbUser = dbsInfosMap.get("REMOTEUSER");
            dbPassword = dbsInfosMap.get("REMOTEPASS");
            ssl = dbsInfosMap.get("REMOTEUSESSL");
        }else{
            host = dbsInfosMap.get("LOCALHOSTNAME");
            port = dbsInfosMap.get("LOCALPORT");
            dbName = dbsInfosMap.get("LOCALDBNAME");
            dbUser = dbsInfosMap.get("LOCALUSER");
            dbPassword = dbsInfosMap.get("LOCALPASS");
            ssl = dbsInfosMap.get("LOCALUSESSL");
        }

        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=" + ssl + "&serverTimezone=UTC";

            connection = DriverManager.getConnection(url, dbUser, dbPassword);
            
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