package metier;

import java.time.LocalDate;

public class Ticket {
	private int id;
	private Machine machine;
	// =============================
	// TODO : 
	// Add more specific type for staff after
	// implement them
	// =============================
	private Staff creator; 
	private Staff technician;
	private String title;
	private String description; 
	private String status;
	private LocalDate open_at;
	private LocalDate closed_at;
	
	public Ticket(int id, Staff creator, Staff technician, String title, String description, String status, LocalDate open_at, LocalDate closed_at) {
		this.id = id;
		this.creator = creator;
		this.technician = technician;
		this.title = title;
		this.description = description;
		this.open_at = open_at;
		this.closed_at = closed_at;
		this.status = null;
		this.SetStatus(status);
	}
	public void SetStatus(String newStatus) {
		if(newStatus == "open" || newStatus == "in_progress" || newStatus == "closed") {
			this.status = newStatus;
		}
	}
}
