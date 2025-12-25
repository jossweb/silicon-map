package application;

import application.sections.admin.AdminPart;
import application.sections.technician.TechnicianPart;
import domain.Admin;
import domain.Staff;
import domain.Technician;
import domain.Context;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

class UserInterface{
    private Stage stage;
    private Staff logUser;
    private Context context;
	public void Dashboard(Stage s, Staff logUser){

        this.logUser = logUser;
        this.stage = s;
        this.context = new Context();

        boolean isadmin = logUser instanceof Admin; 
        logUser.setAvailable(true);

        BorderPane root = new BorderPane();
        root.setId("main-pane");
        
        //head
        GridPane head = new GridPane();
        head.getStyleClass().add("head");

        ColumnConstraints left = new ColumnConstraints();
        left.setHgrow(Priority.ALWAYS);

        ColumnConstraints center = new ColumnConstraints();
        center.setHalignment(HPos.CENTER);

        ColumnConstraints right = new ColumnConstraints();
        right.setHgrow(Priority.ALWAYS);

        head.getColumnConstraints().addAll(left, center, right);

        Label leftLabel = new Label("Silicon Map");
        Label centerLabel = new Label("Status : All good!");
        Label rightLabel = new Label("Technician dashboard");

        if(isadmin){
            rightLabel.setText("Admin dashboard");
        }

        GridPane.setHalignment(leftLabel, HPos.LEFT);
        GridPane.setHalignment(centerLabel, HPos.CENTER);
        GridPane.setHalignment(rightLabel, HPos.RIGHT);

        head.add(leftLabel, 0, 0);
        head.add(centerLabel, 1, 0);
        head.add(rightLabel, 2, 0);

        leftLabel.getStyleClass().addAll("siliconmap-logo");
        centerLabel.getStyleClass().addAll("Status", "Status-normal");
        rightLabel.getStyleClass().addAll("space-type");

        root.setTop(head);

        VBox dashview = new VBox();
        dashview.getStyleClass().add("dashview");

        dashview.setFillWidth(true);
        VBox.setVgrow(dashview, Priority.ALWAYS);

        VBox box; 
        
        if(isadmin){
            box = new AdminPart((Admin)this.logUser, s, context);
        }
        else{
            box = new TechnicianPart((Technician)this.logUser, this.stage, context);
        }
        dashview.getChildren().add(box);
        root.setCenter(dashview);
        Scene scene = new Scene(root,1100,700);
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        this.stage.setScene(scene);
        this.stage.show();

        this.stage.setOnCloseRequest(event -> {
            logUser.setAvailable(false);
        });
    }
}