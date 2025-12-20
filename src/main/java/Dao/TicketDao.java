package Dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Ticket;

public abstract class TicketDao {
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
    public static void createTicketInDb(Ticket newTicket){
        try{
            Connection conn = SingleConnection.GetConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tickets (machine_id, created_by, assigned_to, title, description, status, open_at, closed_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            stmt.setInt(1, newTicket.getMachine().getId());
            stmt.setInt(2, newTicket.getCreator().getId());
            stmt.setInt(3, newTicket.getTechnician().getId());
            stmt.setString(4, newTicket.getTitle());
            stmt.setString(5, newTicket.getDescription());
            stmt.setString(6, newTicket.getStatus());
            stmt.setTimestamp(7, Timestamp.valueOf(newTicket.getOpen_at()));
            stmt.setTimestamp(7, Timestamp.valueOf(newTicket.getOpen_at()));
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("SQL ERROR ! /n explains :" + e);
        }
    }
}
