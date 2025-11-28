package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao<T> {
	
	// =============================
	// TODO : 
	// Change all console output to English
	// =============================

    protected Connection connect;
    protected Statement stmt;
    
    public abstract T create(T obj);
    public abstract T update(T obj);
    public abstract void delete(T obj);

    public void open() {
        try {
        	if(this.connect.isClosed()) {
        		//TODO : let see if we need to use only one connection or 
        		// if we can open multiple connections
        		connect = SingleConnection.getInstance();
                stmt = connect.createStatement();
        	}

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ouverture du DAO : " + e.getMessage());
        }
    }
    public void close() {
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture du Statement : " + e.getMessage());
        }

        SingleConnection.close();
    }
}