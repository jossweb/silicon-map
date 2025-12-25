package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.MessageDao;

public class Message {
    private Staff author;
    private Ticket ticket;
    private String content;

    public Message(Staff author, Ticket ticket, String content){
        this.author = author;
        this.ticket = ticket;
        this.content = content;
    }
    public Message(ResultSet sqlResult){
        try{
            this.author = Staff.getUserById(sqlResult.getInt("author"));
            this.content = sqlResult.getString("message");
        }catch(SQLException e){
			System.out.print("\nERROR : Can't create Message with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
    }
	public Staff getAuthor() {
		return this.author;
	}
	public String getContent() {
		return this.content;
	}
	public Ticket getTicket() {
		return this.ticket;
	}
    public void updateInDb(){
        MessageDao.createMessage(this);
    } 
    public static ArrayList<Message> getForTicketId(int ticketId){
        return MessageDao.getMessagesByTickets(ticketId);
    }
}
