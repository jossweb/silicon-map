package application.components.staff;

import domain.Admin;
import domain.Staff;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

/**
 * Creating card for a user |
 * Création d'une carte pour un utilisateur
 * 
 * @author FIGUEIRAS Jossua
 */
public class StaffCard extends VBox {
    private Staff member;
	 /** 
	 * @param s the staff member to be displayed
	 */
    public StaffCard(Staff s){

        this.member = s;

		HBox nameContainer = new HBox(7);
		Label name = new Label(this.member.getName());
		name.getStyleClass().add("subtitle");
		if(this.member instanceof Admin){
			SVGPath svg = new SVGPath();
			svg.setContent("M20 13c0 5-3.5 7.5-7.66 8.95a1 1 0 0 1-.67-.01C7.5 20.5 4 18 4 13V6a1 1 0 0 1 1-1c2 0 4.5-1.2 6.24-2.72a1.17 1.17 0 0 1 1.52 0C14.51 3.81 17 5 19 5a1 1 0 0 1 1 1z");
			svg.getStyleClass().add("gold-icon");
			nameContainer.getChildren().add(svg);
			name.getStyleClass().add("gold-text");
		}
		nameContainer.getChildren().add(name);

		Label firstName = new Label(this.member.getName());
		firstName.getStyleClass().add("subsubtitle");

		HBox availbleContainer = new HBox(7);
		Circle circle = new Circle(7); 
		Label state = new Label("Online");
		if(this.member.getAvailable()){
			circle.getStyleClass().add("green-dot");
		}else{
			state.setText("Offline");
			circle.getStyleClass().add("red-dot");
		}
		availbleContainer.getChildren().addAll(circle, state);

		this.getStyleClass().add("staff-bubble");
		this.getChildren().addAll(nameContainer, firstName, availbleContainer);
		this.setMaxWidth(Double.MAX_VALUE);
	}
}
