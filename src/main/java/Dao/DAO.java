package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO{
    private Connection connect;
    private Statement stmt;

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