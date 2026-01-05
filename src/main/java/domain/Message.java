package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.MessageDao;

/**
 * Represents a message written by a staff member for a ticket |
 * Représente un message écrit par un membre du personnel pour un ticket.
 * 
 * @author FIGUEIRAS Jossua
 */

public class Message {
    private Staff author;
    private Ticket ticket;
    private String content;

    /**
     * Creates a Message instance with explicit values |
     * Crée une instance Message avec des valeurs explicites.
     * 
     * @param author the staff member who wrote the message |
     *               le membre du personnel qui a écrit le message
     * @param ticket the ticket associated with this message |
     *               le ticket associé à ce message
     * @param content the content of the message |
     *                le contenu du message
     */
    public Message(Staff author, Ticket ticket, String content){
        this.author = author;
        this.ticket = ticket;
        this.content = content;
    }
    /**
     * Creates a Message instance from a SQL result set |
     * Crée une instance Message à partir d'un résultat SQL.
     * 
     * @param sqlResult the SQL result set containing message data |
     * le résultat SQL contenant les données du message
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
     * Returns the author of the message |
     * Retourne l'auteur du message.
     * 
     * @return the Staff instance representing the author |
     * l'instance Staff représentant l'auteur
     */
	public Staff getAuthor() {
		return this.author;
	}
    /**
     * Returns the content of the message |
     * Retourne le contenu du message.
     * 
     * @return the message content |
     * le contenu du message
     */
	public String getContent() {
		return this.content;
	}
    /**
     * Returns the ticket associated with this message |
     * Retourne le ticket associé à ce message.
     * 
     * @return the Ticket instance |
     * l'instance Ticket
     */
	public Ticket getTicket() {
		return this.ticket;
	}
    /**
     * Saves this message in the database |
     * Sauvegarde ce message dans la base de données.
     */
    public void updateInDb(){
        MessageDao.createMessage(this);
    }
    /**
     * Retrieves all messages for a specific ticket ID |
     * Récupère tous les messages pour un ID de ticket spécifique.
     * 
     * @param ticketId the ID of the ticket |
     * l'identifiant du ticket
     * @return a list of messages associated with the ticket |
     * une liste de messages associés au ticket
     */
    public static ArrayList<Message> getForTicketId(int ticketId){
        return MessageDao.getMessagesByTickets(ticketId);
    }
}
