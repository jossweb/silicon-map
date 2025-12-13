package application;

import domain.Admin;
import domain.Staff;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

class UserInterface{
	public void Dashboard(Stage s, Staff logUser) {
            BorderPane root = new BorderPane();
			root.setId("main-pane");
			VBox box;
            if(logUser instanceof Admin){
                box = admin(logUser);
            }
            else{
                box = technician(logUser);
            }
			root.setCenter(box);
			Scene scene = new Scene(root,1920,1080);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			s.setScene(scene);
			s.show();
    }
    private VBox admin(Staff logUser){
        //admin dashboard interface
        VBox div = new VBox();
        Label welcomeText = new Label("Welcome admin " + logUser.getFirst_name());
		welcomeText.setId("welcome-text");

        div.getChildren().add(welcomeText);

        return div;
    }
    private VBox technician(Staff logUser){
        //technician dashboard interface
        VBox div = new VBox();
        Label welcomeText = new Label("Welcome technician " + logUser.getFirst_name());
		welcomeText.setId("welcome-text");

        div.getChildren().add(welcomeText);

        return div;
    }
}