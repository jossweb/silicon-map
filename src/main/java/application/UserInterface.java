package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import domain.Admin;
import domain.Staff;
import domain.Context;
import domain.Storage;
import domain.Compute;
import domain.GpuCompute;
import domain.Network;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
import type.Tuple;
import javafx.scene.layout.VBox;

class UserInterface{
    private Stage stage;
    private Staff logUser;
    private Context context;
	public void Dashboard(Stage s, Staff logUser) {

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
            box = admin();
        }
        else{
            box = technician();
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
    private VBox admin(){
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
        contentPanel.getChildren().setAll(MainStatSummary());

        //button handler navbar
        mainNavButton.setOnAction(s->{
            contentPanel.getChildren().setAll(MainStatSummary());
            selectNavButton(mainNavButton, navButtonsList);
        });

        computeNavButton.setOnAction(s->{
            contentPanel.getChildren().setAll(computePart());
            selectNavButton(computeNavButton, navButtonsList);
        });

        gpuComputeNavButton.setOnAction(s->{
            contentPanel.getChildren().setAll(computeGpuPart());
            selectNavButton(gpuComputeNavButton, navButtonsList);
        });

        storageNavButton.setOnAction(s->{
            contentPanel.getChildren().setAll(storagePart());
            selectNavButton(storageNavButton, navButtonsList);
        });

        networkNavButton.setOnAction(s->{
            contentPanel.getChildren().setAll(networkPart());
            selectNavButton(networkNavButton, navButtonsList);
        });
        staffnavButton.setOnAction(s->{
            contentPanel.getChildren().setAll(staffPart());
            selectNavButton(staffnavButton, navButtonsList);
        });
        ticketNavButton.setOnAction((s->{
            contentPanel.getChildren().setAll(TicketPart());
            selectNavButton(ticketNavButton, navButtonsList);
        }));
        return div;
    }
    // ----------
    // TECHNICIAN DASHBOARD
    // ----------
    private VBox technician(){
        //technician dashboard interface
        VBox div = new VBox();
        div.setAlignment(Pos.TOP_LEFT); 
        Label welcomeText = new Label("Welcome " + this.logUser.getFirst_name());
		welcomeText.getStyleClass().add("subtitle");

        Label statTitle = new Label("Global statistiques ");
		statTitle.getStyleClass().add("subsubtitle");

        div.getChildren().addAll(welcomeText, statTitle);

        return div;
    }
    // ADMIN DASHBOARD DYNAMIC PARTS
    private VBox TicketPart(){
        VBox box = new VBox();
        HBox head = new HBox();
        Label subTitle = new Label("All tickets");
        Button addStaffMember = new Button("+");
        Region midpart = new Region();
        HBox.setHgrow(midpart, Priority.ALWAYS);
        subTitle.getStyleClass().add("subsubtitle");
        addStaffMember.getStyleClass().add("addButton");

        head.getChildren().addAll(subTitle, midpart, addStaffMember);
        box.getChildren().addAll(head, ticketsBubbles());

        addStaffMember.setOnAction(e -> {
            InterfaceAddNewTicket form = new InterfaceAddNewTicket(this.stage, this.context, (Admin)this.logUser);
            form.show();
        });


        return box;
    }
    private VBox staffPart(){
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
            InterfaceAddNewStaff form = new InterfaceAddNewStaff(this.stage);
            form.show();
        });


        return box;
    }
    private VBox networkPart(){
        this.context.updateMachinesList();
        ArrayList<Network> computeList = Network.GetNetworkFromStats(this.context);

        VBox b = new VBox();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        int row = 0;
        int col = 0;
        for (Network c : computeList) {
            Button button = c.createMachineButton(this.stage, this.context);
            grid.add(button, col, row);
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
    private VBox storagePart(){
        this.context.updateMachinesList();
        ArrayList<Storage> computeList = Storage.GetStorageFromStats(this.context);

        VBox b = new VBox();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        int row = 0;
        int col = 0;
        for (Storage c : computeList) {
            Button button = c.createMachineButton(this.stage, this.context);
            grid.add(button, col, row);
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
    private VBox computeGpuPart(){
        this.context.updateMachinesList();
        ArrayList<GpuCompute> computeList = GpuCompute.GetGpuComputeFromStats(this.context);

        VBox b = new VBox();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        int row = 0;
        int col = 0;
        for (GpuCompute c : computeList) {
            Button button = c.createMachineButton(this.stage, this.context);
            grid.add(button, col, row);
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
    private VBox computePart(){
        this.context.updateMachinesList();
        ArrayList<Compute> computeList = Compute.GetComputeFromStats(this.context);

        VBox b = new VBox();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        int row = 0;
        int col = 0;
        for (Compute c : computeList) {
            Button button = c.createMachineButton(this.stage, this.context);
            grid.add(button, col, row);
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
    private VBox MainStatSummary(){
        this.context.updateTemp();
        this.context.updateLoad();
        VBox box = new VBox();
        Label statTitle = new Label("Global statistiques ");
		statTitle.getStyleClass().add("subsubtitle");
        HBox container = new HBox();
        container.setSpacing(10);
        double avgLoad = this.context.getAvgLoad();
        String loadStatus = "normal";
        if(avgLoad>100){
            loadStatus = "error";
        }else if(avgLoad > 75){
            loadStatus = "warning";
        }

        double avgTemp = this.context.getAvgTemp();
        String tempStatus = "normal";
        if(avgTemp>100){
            tempStatus = "error";
        }else if(avgTemp > 75){
            tempStatus = "warning";
        }


        container.getChildren().addAll(createStatBuble(loadStatus, "Average load", String.format("%.1f", avgLoad) + "%"), createStatBuble(tempStatus, "Average temp", String.format("%.1f", avgTemp) + "°"), createStatBuble("error", "test", "0%"), createStatBuble(" ", "test", "0%"));
        
        // Charts part
        HBox chartsPart = new HBox(5);
        LineChart<String, Number> chart1 = createLineChart(this.context.getAvgTempLastInputs(), "Temperature °");
        LineChart<String, Number> chart2 = createLineChart(this.context.getAvgLoadLastInputs(), "Load %");

        chartsPart.getChildren().addAll(chart1, chart2);

        Label evolutionLabel = new Label("Evolution");
        evolutionLabel.getStyleClass().addAll("subsubtitle", "need-gap-y");

        box.getChildren().addAll(statTitle, container, evolutionLabel, chartsPart);
        return box;
    }
    public static LineChart<String, Number> createLineChart(ArrayList<Tuple<Integer, LocalDateTime>> values, String name) {
        CategoryAxis xAxis = new CategoryAxis();

        NumberAxis yAxis = new NumberAxis();
        yAxis.setTickUnit(1);

        LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(name);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        values.forEach(t -> {
            series.getData().add(new XYChart.Data<>(t.getSecond().format(formatter), t.getFirst()));
        });

        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);
        lineChart.setPrefHeight(350);  
        lineChart.getStyleClass().add("need-gap-y");

        return lineChart;
    }
    private void selectNavButton(Button selected, List<Button> buttons) {
        buttons.forEach(b -> b.getStyleClass().remove("navbar-button-is-selected"));
        selected.getStyleClass().add("navbar-button-is-selected");
    }
    private ScrollPane ticketsBubbles(){
        this.context.updateTicketList();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        int col = 0;
        int row = 0;
        for(int i = 0; i < this.context.getListTickets().size(); i++){
            grid.add(this.context.getListTickets().get(i).TicketBubble(), col, row);
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
        return scrollPane;
    }
    private ScrollPane staffMembersBubbles(){
        GridPane container = new GridPane(10, 10);
        this.context.updateStaffMembersList();

        int row = 0;
        int col = 0;
        for(int i = 0; i<this.context.getListStaffMembers().size(); i++){
            container.add(this.context.getListStaffMembers().get(i).staffBubble(), col, row);

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