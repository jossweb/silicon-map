package application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import domain.Admin;
import domain.Staff;
import domain.Statistics;
import domain.Storage;
import domain.Compute;
import domain.GpuCompute;
import domain.Network;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

class UserInterface{
	public void Dashboard(Stage s, Staff logUser) {

        boolean isadmin = logUser instanceof Admin; 
        Statistics statistics = new Statistics();
        logUser.setAvailable(true);
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
            box = admin(logUser, statistics, s);
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

        s.setOnCloseRequest(event -> {
            logUser.setAvailable(false);
        });
    }
    // ----------
    // ADMIN DASHBOARD
    // ----------
    private VBox admin(Staff logUser, Statistics statistics, Stage stage){
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
        Button staffnavButton = new Button("Staff");
        Button ticketNavButton = new Button("Ticket");

        mainNavButton.getStyleClass().addAll("navbar-button", "navbar-button-is-selected");
        computeNavButton.getStyleClass().addAll("navbar-button");
        gpuComputeNavButton.getStyleClass().addAll("navbar-button");
        storageNavButton.getStyleClass().addAll("navbar-button");
        networkNavButton.getStyleClass().addAll("navbar-button");
        staffnavButton.getStyleClass().addAll("navbar-button");
        ticketNavButton.getStyleClass().addAll("navbar-button");

        List<Button> navButtonsList = List.of(mainNavButton, computeNavButton, gpuComputeNavButton, storageNavButton, networkNavButton, staffnavButton, ticketNavButton);

        navButtons.getChildren().addAll(navButtonsList);

        navbar.add(welcomeText, 0, 0);
        navbar.add(navButtons, 1, 0);

        StackPane contentPanel = new StackPane();

        div.getChildren().addAll(navbar, contentPanel);
        contentPanel.getChildren().setAll(MainStatSummary(statistics));

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
        staffnavButton.setOnAction(s->{
            contentPanel.getChildren().setAll(staffPart(statistics, stage));
            selectNavButton(staffnavButton, navButtonsList);
        });
        ticketNavButton.setOnAction((s->{
            contentPanel.getChildren().setAll(TicketPart(statistics));
            selectNavButton(ticketNavButton, navButtonsList);
        }));
        return div;
    }
    // ----------
    // TECHNICIAN DASHBOARD
    // ----------
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
    // ----------
    // ADMIN DASHBOARD DYNAMIC PARTS
    // ----------
    private VBox TicketPart(Statistics s){
        VBox box = new VBox();
        s.updateTicketList();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        int col = 0;
        int row = 0;
        for(int i = 0; i < s.getListTickets().size(); i++){
            grid.add(s.getListTickets().get(i).TicketBubble(), col, row);
            if(col == 1){
                col = 0;
                row ++;
            }else{
                col ++;
            }
        }
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);  
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.getStyleClass().add("scroll-pane");
        box.getChildren().add(scrollPane);
        return box;
    }
    private VBox staffPart(Statistics statistics, Stage primaryStage){
        VBox box = new VBox();
        HBox head = new HBox();
        Label subTitle = new Label("Staff members");
        Button addStaffMember = new Button("+");
        Region midpart = new Region();
        HBox.setHgrow(midpart, Priority.ALWAYS);

        subTitle.getStyleClass().add("subsubtitle");
        addStaffMember.getStyleClass().add("addButton");

        head.getChildren().addAll(subTitle, midpart, addStaffMember);
        box.getChildren().addAll(head, staffMembersBubbles());

        addStaffMember.setOnAction(e -> {
            InterfaceAddNewStaff form = new InterfaceAddNewStaff(primaryStage);
            form.show();
        });


        return box;
    }
    private VBox networkPart(Statistics statistics){
        statistics.updateMachinesList();
        ArrayList<Network> computeList = Network.GetNetworkFromStats(statistics);

        VBox b = new VBox();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        int row = 0;
        int col = 0;
        for (Network c : computeList) {
            VBox box = c.createMachineButton();
            grid.add(box, col, row);
            col++;
            if (col >= 5) {
                col = 0;
                row++;
            }
        }
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);  
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.getStyleClass().add("scroll-pane");

        b.getChildren().add(scrollPane);
        return b;
    } 
    private VBox storagePart(Statistics statistics){
        statistics.updateMachinesList();
        ArrayList<Storage> computeList = Storage.GetStorageFromStats(statistics);

        VBox b = new VBox();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        int row = 0;
        int col = 0;
        for (Storage c : computeList) {
            VBox box = c.createMachineButton();
            grid.add(box, col, row);
            col++;
            if (col >= 5) {
                col = 0;
                row++;
            }
        }
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);  
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.getStyleClass().add("scroll-pane");

        b.getChildren().add(scrollPane);
        return b;
    }
    private VBox computeGpuPart(Statistics statistics){
        statistics.updateMachinesList();
        ArrayList<GpuCompute> computeList = GpuCompute.GetGpuComputeFromStats(statistics);

        VBox b = new VBox();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        int row = 0;
        int col = 0;
        for (GpuCompute c : computeList) {
            VBox box = c.createMachineButton();
            grid.add(box, col, row);
            col++;
            if (col >= 5) {
                col = 0;
                row++;
            }
        }
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);  
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.getStyleClass().add("scroll-pane");

        b.getChildren().add(scrollPane);
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
            VBox box = c.createMachineButton();
            grid.add(box, col, row);
            col++;
            if (col >= 5) {
                col = 0;
                row++;
            }
        }
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);  
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.getStyleClass().add("scroll-pane");

        b.getChildren().add(scrollPane);
        return b;
    }
    private VBox MainStatSummary(Statistics statistics){
        VBox box = new VBox();
        Label statTitle = new Label("Global statistiques ");
		statTitle.getStyleClass().add("subsubtitle");
        HBox container = new HBox();
        container.setSpacing(10);
        container.getChildren().addAll(createStatBuble("normal", "Average load", String.format("%.1f", statistics.getAvgLoad()) + "%"), createStatBuble("warning", "Average temp", String.format("%.1f", statistics.getAvgTemp()) + "°"), createStatBuble("error", "test", "0%"), createStatBuble(" ", "test", "0%"));
        
        box.getChildren().addAll(statTitle, container);
        return box;
    }
    private void selectNavButton(Button selected, List<Button> buttons) {
        buttons.forEach(b -> b.getStyleClass().remove("navbar-button-is-selected"));
        selected.getStyleClass().add("navbar-button-is-selected");
    }
    private ScrollPane staffMembersBubbles(){
        GridPane container = new GridPane(10, 10);
        Statistics s = new Statistics();
        s.updateStaffMembersList();

        int row = 0;
        int col = 0;
        for(int i = 0; i<s.getListStaffMembers().size(); i++){
            container.add(s.getListStaffMembers().get(i).staffBubble(), col, row);

            if(col==1){
                col=0;
                row++;
            }else{
                col++;
            }
        }
        ColumnConstraints constraint = new ColumnConstraints();
        constraint.setHgrow(Priority.ALWAYS);
        constraint.setFillWidth(true);
        container.getColumnConstraints().addAll(constraint, constraint);

        ScrollPane scroll = new ScrollPane(container);
        scroll.setFitToWidth(true);  
        scroll.setFitToHeight(false);
        scroll.setPannable(true);
        return scroll;

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