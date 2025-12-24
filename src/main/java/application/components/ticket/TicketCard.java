package application.components.ticket;

import domain.Ticket;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TicketCard extends VBox{
	private Ticket ticket;
	public TicketCard(Ticket t){
		this.ticket = t;
		Label title = new Label(ticket.getTitle());
		title.getStyleClass().add("subtitle");
		Label descriptionPreview = new Label(ticket.getDescription());
		descriptionPreview.getStyleClass().add("subsubtitle");
		Label createdBy = new Label("Created by " + ticket.getCreator().getName());
		Label technicianOn = new Label("For " + ticket.getTechnician().getName());
		this.getStyleClass().add("staff-bubble");
		this.getChildren().addAll(title, descriptionPreview, createdBy, technicianOn);
		this.setMaxWidth(Double.MAX_VALUE); 
	}
}
