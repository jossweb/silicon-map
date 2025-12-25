package application.components.ticket;

import application.InterfaceTicketInfo;
import domain.Staff;
import domain.Ticket;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicketCard extends Button{
	private Ticket ticket;
	public TicketCard(Ticket t, Staff staff, Stage stage){
		this.ticket = t;
		Label title = new Label(ticket.getTitle());
		title.getStyleClass().add("subtitle");
		Label descriptionPreview = new Label(ticket.getDescription());
		descriptionPreview.getStyleClass().add("subsubtitle");
		Label createdBy = new Label("Created by " + ticket.getCreator().getName());
		Label technicianOn = new Label("For " + ticket.getTechnician().getName());
		VBox box = new VBox();
		box.getChildren().addAll(title, descriptionPreview, createdBy, technicianOn);

		this.getStyleClass().add("staff-bubble");
		this.setMaxWidth(Double.MAX_VALUE);
		this.setGraphic(box);

		this.setOnAction(button->{
			new InterfaceTicketInfo(stage, staff, t).show();;
		});
	}
}
