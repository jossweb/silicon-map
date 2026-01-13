package Dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import domain.Ticket;

/**
 * All SQL interactions related to the tickets
 * 
 * @author FIGUEIRAS Jossua
 */
public abstract class TicketDao {
    /**
	 * Get ticket by id
	 * 
	 * @param id ticket's id
	 * 
	 * @return Ticket
	 */
    public static Ticket getTicket(int id){
        try{
            Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT id, machine_id, created_by, assigned_to, title, description, status, open_at, closed_at FROM tickets WHERE id = ?;");
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            if(result.next()){
                return new Ticket(result);
            }
        }catch(SQLException e) {
			System.out.println("SQL ERROR ! /n explains :" + e);
		}
        return null;
    }
    /**
	 * Get all tickets
	 * 
	 * @return ArrayList who contain all tickets
	 */
    public static ArrayList<Ticket> getAllTicket(){
        ArrayList<Ticket> list = new ArrayList<Ticket>();
        try{
            Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT id, machine_id, created_by, assigned_to, title, description, status, open_at, closed_at FROM tickets");
            ResultSet result = stmt.executeQuery();

            while(result.next()){
                list.add(new Ticket(result));
            }
        }catch(SQLException e) {
			System.out.println("SQL ERROR ! /n explains :" + e);
		}
        return list;
    }
    /**
	 * Create a tickets in database
	 * 
     * @param newTicket ticket
     * 
	 * @return new ticket't id 
	 */
    public static int createTicketInDb(Ticket newTicket){
        try{
            Connection conn = SingleConnection.GetConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tickets (machine_id, created_by, assigned_to, title, description, status, open_at, closed_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, newTicket.getMachine().getId());
            stmt.setInt(2, newTicket.getCreator().getId());
            stmt.setInt(3, newTicket.getTechnician().getId());
            stmt.setString(4, newTicket.getTitle());
            stmt.setString(5, newTicket.getDescription());
            stmt.setString(6, newTicket.getStatus());
            stmt.setTimestamp(7, Timestamp.valueOf(newTicket.getOpen_at()));
            if (newTicket.getClosed_at() != null) {
                stmt.setTimestamp(8, Timestamp.valueOf(newTicket.getClosed_at()));
            } else {
                stmt.setNull(8, java.sql.Types.TIMESTAMP);
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }else{
                System.out.print("Error : can't create ticket in db");
            }

        }catch(SQLException e){
            System.out.println("SQL ERROR ! /n explains :" + e);
        }
        return 0;
    }
    /**
	 * Update a tickets in database
	 * 
     * @param t ticket
	 */
    public static void updateTicket(Ticket t){
        try{
            Connection conn = SingleConnection.GetConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE tickets SET machine_id = ?, created_by = ?, assigned_to = ?, title = ?, description = ?, status = ?, open_at = ?, closed_at = ? WHERE id = ?;");
            
            stmt.setInt(1, t.getMachine().getId());
            stmt.setInt(2, t.getCreator().getId());
            stmt.setInt(3, t.getTechnician().getId());
            stmt.setString(4, t.getTitle());
            stmt.setString(5, t.getDescription());
            stmt.setString(6, t.getStatus());
            stmt.setTimestamp(7, Timestamp.valueOf(t.getOpen_at()));
            if (t.getClosed_at() != null) {
                stmt.setTimestamp(8, Timestamp.valueOf(t.getClosed_at()));
            } else {
                stmt.setNull(8, java.sql.Types.TIMESTAMP);
            }

            stmt.setInt(9, t.getId());

            stmt.executeUpdate();

        }catch(SQLException e){
            System.out.println("SQL ERROR ! /n explains :" + e);
        }
    }
}
