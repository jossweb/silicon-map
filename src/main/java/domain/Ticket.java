package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import Dao.TicketDao;

/**
 * Represents a support ticket in the system
 * 
 * @author FIGUEIRAS Jossua
 */

public class Ticket {
	private int id;
	private Machine machine;
	private Admin creator; 
	private Technician technician;
	private String title;
	private String description; 
	private String status;
	private LocalDateTime open_at;
	private LocalDateTime closed_at;

	/**
     * Creates a Ticket instance with explicit values
     *
     * @param id the unique identifier of the ticket
     * @param m the machine associated with the ticket
     * @param creator the admin who created the ticket
     * @param technician the technician assigned to the ticket 
     * @param title the title of the ticket
     * @param description the detailed description of the issue
     * @param status the current status of the ticket ("open", "in_progress", "closed")
     * @param open_at the date and time the ticket was opened
     * @param closed_at the date and time the ticket was closed
     */
	public Ticket(int id, Machine m, Admin creator, Technician technician, String title, String description, String status, LocalDateTime open_at, LocalDateTime closed_at) {
		this.id = id;
		this.machine = m;
		this.creator = creator;
		this.technician = technician;
		this.title = title;
		this.description = description;
		this.open_at = open_at;
		this.closed_at = closed_at;
		this.status = status;
	}
	/**
     * Creates a Ticket instance from a SQL result set 
     *
     * @param result the SQL result set containing ticket data
     */
	public Ticket(ResultSet result){
		try{
			this.id = result.getInt("id");
			this.machine = Machine.getMachine(result.getInt("machine_id"));
			this.creator = Admin.getAdminById(result.getInt("created_by"));
			this.technician = Technician.getTechncianById(result.getInt("assigned_to"));
			this.title = result.getString("title");
			this.description = result.getString("description");
			if(result.getTimestamp("closed_at") != null){
				this.open_at = result.getTimestamp("open_at").toLocalDateTime();
			}else{
				this.open_at = null;
			}
			if(result.getTimestamp("closed_at") != null){
				this.closed_at = result.getTimestamp("closed_at").toLocalDateTime();
			}else{
				this.closed_at = null;
			}
			this.status = result.getString("status");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Staff with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	/**
     * Creates this ticket in the database
     */
	public void createTicket(){
		this.id = TicketDao.createTicketInDb(this);
	}

    /**
     * Sets the status of the ticket if valid
     *
     * @param newStatus the new status ("open", "in_progress", "closed") 
     */
	public void SetStatus(String newStatus) {
		if(newStatus == "open" || newStatus == "in_progress" || newStatus == "closed") {
			this.status = newStatus;
		}
	}
	/**
     * Closes the ticket and sets the closing timestamp
     */
	public void close(){
		this.SetStatus("closed");
		this.closed_at = LocalDateTime.now();
	}
	public int getId() {
		return id;
	}
	public Machine getMachine() {
		return machine;
	}
	public Admin getCreator() {
		return creator;
	}
	public Technician getTechnician() {
		return technician;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getStatus() {
		return status;
	}
	public LocalDateTime getOpen_at() {
		return open_at;
	}
	public LocalDateTime getClosed_at() {
		return closed_at;
	}
	/**
     * Updates this ticket in the database
     */
	public void updateInDb(){
		TicketDao.updateTicket(this);
	}
}
