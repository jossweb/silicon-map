package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO<T> {

    protected Connection connect;
    protected Statement stmt;
    
    public abstract T create(T obj);
    public abstract T update(T obj);
    public abstract void delete(T obj);

    public void open() {
        try {
            connect = SingleConnection.getInstance();

            stmt = connect.createStatement();

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