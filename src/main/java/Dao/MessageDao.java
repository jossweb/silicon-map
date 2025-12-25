package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Message;

public abstract class MessageDao {

    public static ArrayList<Message> getMessagesByTickets(int ticketsId){
        ArrayList<Message> response = new ArrayList<Message>();

        try {
            Connection conn = SingleConnection.GetConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT id, message, tickets, author FROM message WHERE tickets = ?;");
            stmt.setInt(1, ticketsId);
            ResultSet result = stmt.executeQuery();

            while(result.next()){
                response.add(new Message(result));
            }
        }catch(SQLException e) {
			throw new RuntimeException(e);
		}
        return response;
    }
    public static void createMessage(Message m){
        try {
            Connection conn = SingleConnection.GetConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Message (message, tickets, author) VALUES (?, ?, ?);");
            stmt.setString(1, m.getContent());
            stmt.setInt(2, m.getTicket().getId());
            stmt.setInt(3, m.getAuthor().getId());
            stmt.executeUpdate();

        }catch(SQLException e) {
			throw new RuntimeException(e);
		}
    }
}
