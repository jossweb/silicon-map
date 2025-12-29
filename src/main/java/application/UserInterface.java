package application;

import Dao.SingleConnection;
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
import type.Tuple;
import javafx.scene.layout.VBox;

class UserInterface{
    private Stage stage;
    private Staff logUser;
    private Context context;
	public void Dashboard(Stage s, Staff logUser){

        this.logUser = logUser;
        this.stage = s;
        this.context = new Context();

        this.stage.setMinWidth(900);
        this.stage.setMinHeight(600);

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

        Tuple<String, String> statusLabelContentAndStyle = this.definedStatus();

        Label leftLabel = new Label("Silicon Map");
        Label centerLabel = new Label(statusLabelContentAndStyle.getFirst());
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
        centerLabel.getStyleClass().addAll("Status", statusLabelContentAndStyle.getSecond());
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
            SingleConnection.close();
        });
    }
    public Tuple<String, String> definedStatus(){
        this.context.updateMachinesList();

        int nbMaintenanceMachine = (int)this.context.getMachines().stream()
                                    .filter(m->m.getStatus().equals("Maintenance"))
                                    .count();
        if(nbMaintenanceMachine<1){
            return new Tuple<String,String>("No machine detected in the database", "Status-error");
        }

        int totalMachine = (int)this.context.getMachines().stream()
                                    .count();

        int maintenancePercentage = (nbMaintenanceMachine*100)/totalMachine;
        if(maintenancePercentage>=50){
            return new Tuple<String, String>("Status : a lot of problems", "Status-error");
        }else if(maintenancePercentage>=25){
            return new Tuple<String, String>("Status : some problems", "Status-warning");
        }else{
            return new Tuple<String, String>("Status : All good!", "Status-normal");
        }
    }
}