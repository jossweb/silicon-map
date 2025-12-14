package application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import domain.Admin;
import domain.Staff;
import domain.Statistics;
import domain.Compute;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
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
			Scene scene = new Scene(root,1100,700);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			s.setScene(scene);
			s.show();
    }
    private VBox admin(Staff logUser, Statistics statistics){
        //admin dashboard interface
        VBox div = new VBox();
        div.setAlignment(Pos.TOP_LEFT);

        // Navbar
        GridPane navbar = new GridPane();
        navbar.getStyleClass().add("navbar");
        navbar.setHgap(20);

        ColumnConstraints left = new ColumnConstraints();
        left.setHgrow(Priority.ALWAYS);

        ColumnConstraints right = new ColumnConstraints();
        right.setHgrow(Priority.ALWAYS);

        navbar.getColumnConstraints().addAll(left, right);

        Label welcomeText = new Label("Welcome " + logUser.getFirst_name());
        welcomeText.getStyleClass().add("subtitle");
        GridPane.setHalignment(welcomeText, HPos.LEFT);

        HBox navButtons = new HBox();
        navButtons.setAlignment(Pos.CENTER_RIGHT);

        Button mainNavButton = new Button("Main");
        Button computeNavButton = new Button("Compute");
        Button gpuComputeNavButton = new Button("GPU Compute");
        Button storageNavButton = new Button("Storage");
        Button networkNavButton = new Button("Network");

        mainNavButton.getStyleClass().addAll("navbar-button", "navbar-button-is-selected");
        computeNavButton.getStyleClass().addAll("navbar-button");
        gpuComputeNavButton.getStyleClass().addAll("navbar-button");
        storageNavButton.getStyleClass().addAll("navbar-button");
        networkNavButton.getStyleClass().addAll("navbar-button");

        navButtons.getChildren().addAll(mainNavButton, computeNavButton, gpuComputeNavButton, storageNavButton, networkNavButton);

        navbar.add(welcomeText, 0, 0);
        navbar.add(navButtons, 1, 0);

        Label statTitle = new Label("Global statistiques ");
		statTitle.getStyleClass().add("subsubtitle");

        StackPane contentPanel = new StackPane();

        div.getChildren().addAll(navbar, statTitle, contentPanel);
        contentPanel.getChildren().setAll(MainStatSummary(statistics));

        List<Button> navButtonsList = List.of(mainNavButton, computeNavButton, gpuComputeNavButton, storageNavButton, networkNavButton);

        //button handler navbar
        mainNavButton.setOnAction(s->{
            contentPanel.getChildren().setAll(MainStatSummary(statistics));
            selectNavButton(mainNavButton, navButtonsList);
        });

        computeNavButton.setOnAction(s->{
            contentPanel.getChildren().setAll(computePart(statistics));
            selectNavButton(computeNavButton, navButtonsList);
        });

        gpuComputeNavButton.setOnAction(s->{
            contentPanel.getChildren().setAll(computeGpuPart(statistics));
            selectNavButton(gpuComputeNavButton, navButtonsList);
        });

        storageNavButton.setOnAction(s->{
            contentPanel.getChildren().setAll(storagePart(statistics));
            selectNavButton(storageNavButton, navButtonsList);
        });

        networkNavButton.setOnAction(s->{
            contentPanel.getChildren().setAll(networkPart(statistics));
            selectNavButton(networkNavButton, navButtonsList);
        });

        return div;
    }
    private void selectNavButton(Button selected, List<Button> buttons) {
        buttons.forEach(b -> b.getStyleClass().remove("navbar-button-is-selected"));
        selected.getStyleClass().add("navbar-button-is-selected");
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
    private VBox networkPart(Statistics statistics){
        VBox b = new VBox();
        b.getChildren().add(new Label("network"));
        return b;
    } 
    private VBox storagePart(Statistics statistics){
        VBox b = new VBox();
        b.getChildren().add(new Label("storage"));
        return b;
    }
    private VBox computeGpuPart(Statistics statistics){
        VBox b = new VBox();
        b.getChildren().add(new Label("compute gpu"));
        return b;
    }
private VBox computePart(Statistics statistics){
    statistics.updateMachinesList();
    ArrayList<Compute> computeList = Compute.GetComputeFromStats(statistics);

    VBox b = new VBox();
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    int row = 0;
    int col = 0;
    for (Compute c : computeList) {
        VBox box = createMachineButton(c.getHostname(), c.getOs(), c.getStatus(), c.getIp_address());
        grid.add(box, col, row);
        col++;
        if (col >= 5) {
            col = 0;
            row++;
        }
    }

    b.getChildren().add(grid);
    return b;
}
    private HBox MainStatSummary(Statistics statistics){
        HBox container = new HBox();
        container.setSpacing(10);
        container.getChildren().addAll(createStatBuble("normal", "Average load", statistics.GetAvgLoad().toString() + "%"), createStatBuble("warning", "Average temp", statistics.GetAvgTemp().toString() + "°"), createStatBuble("error", "test", "0%"), createStatBuble(" ", "test", "0%"));
        
        return container;

    }
    private VBox createMachineButton(String hostname, String os, String status, String ip_address){
        VBox b = new VBox();
        b.setAlignment(Pos.CENTER);

        Label l = new Label(hostname);
        l.getStyleClass().add("bubble-title");
        Label ip = new Label(ip_address);

        if(status.equals("Online")){
            b.getStyleClass().add("machine-bubble-normal");
        }else if(status.equals("Maintenance")){
            b.getStyleClass().add("machine-bubble-warning");
        }else if(status.equals("Offline")){
            b.getStyleClass().add("machine-bubble-error");
        }
        b.getChildren().addAll(l, ip);
        return b;
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