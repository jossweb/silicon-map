package application;

import Dao.SingleConnection;
import application.sections.admin.AdminPart;
import application.sections.technician.TechnicianPart;
import domain.Admin;
import domain.Staff;
import domain.Technician;
import domain.Context;
import javafx.application.Platform;
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

/**
 * User interface base. |
 * Base de l'interface utilisateur.
 * 
 * @author FIGUEIRAS Jossua
 */
class UserInterface{
    private Stage stage;
    private Staff logUser;
    private Context context;
    /**
     * Opens the main dashboard for the logged-in user. |
     * Ouvre le tableau de bord principal pour l'utilisateur connecté.
     * 
     * @param s the primary stage | le stage principal
     * @param logUser the logged-in user | l'utilisateur connecté
     */
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
        
        if(isadmin){
            AdminPart box = new AdminPart((Admin)this.logUser, s, context);
            dashview.getChildren().add(box);
            Thread refresh = new Thread(() -> {
                while (true) {
                    try{
                        Thread.sleep(10000);
                        Platform.runLater(() -> box.refresh()); 
                    }catch(Exception e){
                        System.out.print("Error : refresh thread : \n " + e);
                    } 
                }
            });
            refresh.setDaemon(true);
            refresh.start();
        }
        else{
            TechnicianPart box = new TechnicianPart((Technician)this.logUser, this.stage, context);
            dashview.getChildren().add(box);
            Thread refresh = new Thread(() -> {
                while(true){
                    try{
                        Thread.sleep(10000);
                        Platform.runLater(() -> box.refresh());
                    }catch(Exception e){
                        System.out.print("Error : refresh thread : \n " + e);
                    }
                }
            });
            refresh.setDaemon(true);
            refresh.start();
        }
        
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

        int totalMachine = (int)this.context.getMachines().stream()
            .count();
                                    
        if(totalMachine<1){
            return new Tuple<String,String>("No machine detected in the database", "Status-error");
        }

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