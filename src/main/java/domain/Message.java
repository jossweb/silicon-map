package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.MessageDao;

/**
 * Represents a message written by a staff member for a ticket
 * 
 * @author FIGUEIRAS Jossua
 */

public class Message {
    private Staff author;
    private Ticket ticket;
    private String content;

    /**
     * Creates a Message instance with explicit values
     * 
     * @param author the staff member who wrote the message
     * @param ticket the ticket associated with this message
     * @param content the content of the message
     */
    public Message(Staff author, Ticket ticket, String content){
        this.author = author;
        this.ticket = ticket;
        this.content = content;
    }
    /**
     * Creates a Message instance from a SQL result set
     * 
     * @param sqlResult the SQL result set containing message data
     */
    public Message(ResultSet sqlResult){
        try{
            this.author = Staff.getUserById(sqlResult.getInt("author"));
            this.content = sqlResult.getString("message");
        }catch(SQLException e){
			System.out.print("\nERROR : Can't create Message with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
    }
    /**
     * Returns the author of the message
     * 
     * @return the Staff instance representing the author
     */
	public Staff getAuthor() {
		return this.author;
	}
    /**
     * Returns the content of the message
     * 
     * @return the message content 
     */
	public String getContent() {
		return this.content;
	}
    /**
     * Returns the ticket associated with this message
     * 
     * @return the Ticket instance
     */
	public Ticket getTicket() {
		return this.ticket;
	}
    /**
     * Saves this message in the database
     */
    public void updateInDb(){
        MessageDao.createMessage(this);
    }
    /**
     * Retrieves all messages for a specific ticket ID
     * 
     * @param ticketId the ID of the ticket
     * @return a list of messages associated with the ticket
     */
    public static ArrayList<Message> getForTicketId(int ticketId){
        return MessageDao.getMessagesByTickets(ticketId);
    }
}
