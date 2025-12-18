package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
	
	public Ticket(int id, Admin creator, Technician technician, String title, String description, String status, LocalDateTime open_at, LocalDateTime closed_at) {
		this.id = id;
		this.creator = creator;
		this.technician = technician;
		this.title = title;
		this.description = description;
		this.open_at = open_at;
		this.closed_at = closed_at;
		this.status = status;
	}
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
	public void SetStatus(String newStatus) {
		if(newStatus == "open" || newStatus == "in_progress" || newStatus == "closed") {
			this.status = newStatus;
		}
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
	public VBox TicketBubble(){
		Label title = new Label(this.title);
		title.getStyleClass().add("subtitle");
		Label descriptionPreview = new Label(this.description);
		descriptionPreview.getStyleClass().add("subsubtitle");
		Label createdBy = new Label("Created by " + this.creator.getName());
		Label technicianOn = new Label("For " + this.technician.getName());
		VBox bubble = new VBox();
		bubble.getStyleClass().add("staff-bubble");
		bubble.getChildren().addAll(title, descriptionPreview, createdBy, technicianOn);
		bubble.setMaxWidth(Double.MAX_VALUE); 
		return bubble;
	}
}
