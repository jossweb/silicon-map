package application;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import domain.Admin;
import domain.Staff;
import domain.Statistics;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

class UserInterface{
	public void Dashboard(Stage s, Staff logUser) {

            boolean isadmin = logUser instanceof Admin; 

            Statistics statistics = new Statistics();
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

            executor.scheduleAtFixedRate(() -> {
                statistics.updateTemp();
                statistics.updateLoad();

            }, 0, 5, TimeUnit.SECONDS);

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
                box = admin(logUser, statistics);
            }
            else{
                box = technician(logUser);
            }
            dashview.getChildren().add(box);
			root.setCenter(dashview);
			Scene scene = new Scene(root,1920,1080);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			s.setScene(scene);
			s.show();
    }
    private VBox admin(Staff logUser, Statistics statistics){
        //admin dashboard interface
        VBox div = new VBox();
        div.setAlignment(Pos.TOP_LEFT); 
        Label welcomeText = new Label("Welcome " + logUser.getFirst_name());
		welcomeText.getStyleClass().add("subtitle");

        Label statTitle = new Label("Global statistiques ");
		statTitle.getStyleClass().add("subsubtitle");

        div.getChildren().addAll(welcomeText, statTitle, MainStatSummay(statistics));

        return div;
    }
    private VBox technician(Staff logUser){
        //technician dashboard interface
        VBox div = new VBox();
        div.setAlignment(Pos.TOP_LEFT); 
        Label welcomeText = new Label("Welcome " + logUser.getFirst_name());
		welcomeText.getStyleClass().add("subtitle");

        Label statTitle = new Label("Global statistiques ");
		statTitle.getStyleClass().add("subsubtitle");

        div.getChildren().addAll(welcomeText, statTitle);

        return div;
    }
    private HBox MainStatSummay(Statistics statistics){
        HBox container = new HBox();
        container.setSpacing(10);
        container.getChildren().addAll(createStatBuble("normal", "Average load", statistics.GetAvgLoad().toString()), createStatBuble("warning", "Average temp", statistics.GetAvgTemp().toString()), createStatBuble("error", "test", "0%"), createStatBuble(" ", "test", "0%"));
        
        return container;

    }
    private VBox createStatBuble(String status, String labelContent, String ValueContent){
        VBox bubble = new VBox();
            bubble.setSpacing(5);
            bubble.getStyleClass().add("stats"); 
            bubble.setAlignment(Pos.CENTER);
            HBox.setHgrow(bubble, Priority.ALWAYS);  
            bubble.setMaxWidth(Double.MAX_VALUE);

            Label num = new Label(ValueContent);
            num.getStyleClass().addAll("stats-num");
            Label label = new Label(labelContent);
            if(status == "warning"){
                num.getStyleClass().addAll("Status-warning");
                label.getStyleClass().addAll("Status-warning");
            }else if(status == "error"){
                num.getStyleClass().addAll("Status-error");
                label.getStyleClass().addAll("Status-error");
            }else if(status == "normal"){
                 num.getStyleClass().addAll("Status-normal");
                label.getStyleClass().addAll("Status-normal");
            }
            bubble.getChildren().addAll(num, label);

            return bubble;
    }
}