package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Managing the connection to the SQL server
 * 
 * @author EVANGELISTA Thomas
 */
public abstract class DAO{
    private Connection connect;
    private Statement stmt;

    /**
     * Open the SQL connection 
     */
    public void open() {
        try {
        	if(this.connect.isClosed()) {
        		connect = SingleConnection.GetConnection();
                stmt = connect.createStatement();
        	}

        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }
    }
    /**
     * Close the SQL connection 
     */
    public void close() {
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }

        SingleConnection.close();
    }
}