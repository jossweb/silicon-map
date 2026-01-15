package application.components;

import java.util.List;

import application.interfaces.refreshable;
import application.sections.MainPart;
import application.sections.TicketPart;
import application.sections.admin.MachinesParts;
import application.sections.admin.StaffPart;
import domain.Admin;
import domain.Compute;
import domain.Context;
import domain.GpuCompute;
import domain.Network;
import domain.Staff;
import domain.Storage;
import domain.Technician;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Navbar of principal stage 
 * 
 * @author FIGUEIRAS Jossua
 */
public class Navbar extends GridPane implements refreshable{
    private Staff user;
    private Context context;
    private Stage stage;
    private StackPane contentPanel;
    private String isSelected;
    /**
     * Admin version 
     * 
     * @param user the Admin currently logged in
     * @param context the Context object containing machines, tickets, and other data
     * @param stage the Stage on which the interface is displayed
     * @param contentPanel the StackPane where the content of each section is displayed
     */
    public Navbar(Admin user, Context context, Stage stage, StackPane contentPanel){

        this.user = user;
        this.context = context;
        this.stage = stage;
        this.contentPanel = contentPanel;
        this.isSelected = "main";

        this.getStyleClass().add("navbar");
        this.setHgap(20);

        ColumnConstraints left = new ColumnConstraints();
        left.setHgrow(Priority.ALWAYS);

        ColumnConstraints right = new ColumnConstraints();
        right.setHgrow(Priority.ALWAYS);

        this.getColumnConstraints().addAll(left, right);

        Label welcomeText = new Label("Welcome " + user.getFirst_name());
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

        this.add(welcomeText, 0, 0);
        this.add(navButtons, 1, 0);

        this.contentPanel.getChildren().setAll(new MainPart(user, this.context));

        //button handler navbar
        mainNavButton.setOnAction(s->{
            this.contentPanel.getChildren().setAll(new MainPart(user, this.context));
            selectNavButton(mainNavButton, navButtonsList);
            this.isSelected = "main";
        });

        computeNavButton.setOnAction(s->{
            this.contentPanel.getChildren().setAll(new MachinesParts<Compute>(this.context, this.stage, Compute.class));
            selectNavButton(computeNavButton, navButtonsList);
            this.isSelected = "compute";
        });

        gpuComputeNavButton.setOnAction(s->{
            this.contentPanel.getChildren().setAll(new MachinesParts<GpuCompute>(this.context, this.stage, GpuCompute.class));
            selectNavButton(gpuComputeNavButton, navButtonsList);
            this.isSelected = "gpu-compute";
        });

        storageNavButton.setOnAction(s->{
            this.contentPanel.getChildren().setAll(new MachinesParts<Storage>(this.context, this.stage, Storage.class));
            selectNavButton(storageNavButton, navButtonsList);
            this.isSelected = "storage";
        });

        networkNavButton.setOnAction(s->{
            this.contentPanel.getChildren().setAll(new MachinesParts<Network>(this.context, this.stage, Network.class));
            selectNavButton(networkNavButton, navButtonsList);
            this.isSelected = "network";
        });
        staffnavButton.setOnAction(s->{
            this.contentPanel.getChildren().setAll(new StaffPart(this.stage, this.context));
            selectNavButton(staffnavButton, navButtonsList);
            this.isSelected = "staff";
        });
        ticketNavButton.setOnAction((s->{
            this.contentPanel.getChildren().setAll(new TicketPart(user, this.stage, this.context));
            selectNavButton(ticketNavButton, navButtonsList);
            this.isSelected = "tickets";
        }));
    }
    /**
     * Technician version 
     * 
     * @param user the Technician currently logged in
     * @param context the Context object containing machines, tickets, and other data
     * @param stage the Stage on which the interface is displayed
     * @param contentPanel the StackPane where the content of each section is displayed
     */
    public Navbar(Technician user, Context context, Stage stage, StackPane contentPanel){

        this.context = context;
        this.stage = stage;
        this.contentPanel = contentPanel;
        this.user = user;
        this.isSelected = "main";

        this.getStyleClass().add("navbar");
        this.setHgap(20);

        ColumnConstraints left = new ColumnConstraints();
        left.setHgrow(Priority.ALWAYS);

        ColumnConstraints right = new ColumnConstraints();
        right.setHgrow(Priority.ALWAYS);

        this.getColumnConstraints().addAll(left, right);

        Label welcomeText = new Label("Welcome " + user.getFirst_name());
        welcomeText.getStyleClass().add("subtitle");
        GridPane.setHalignment(welcomeText, HPos.LEFT);

        HBox navButtons = new HBox();
        navButtons.setAlignment(Pos.CENTER_RIGHT);

        Button mainNavButton = new Button("Main");
        Button ticketNavButton = new Button("Ticket");

        mainNavButton.getStyleClass().addAll("navbar-button", "navbar-button-is-selected");
        ticketNavButton.getStyleClass().addAll("navbar-button");

        List<Button> navButtonsList = List.of(mainNavButton, ticketNavButton);

        navButtons.getChildren().addAll(navButtonsList);

        this.add(welcomeText, 0, 0);
        this.add(navButtons, 1, 0);

        this.contentPanel.getChildren().setAll(new MainPart(user, this.context));
        this.getStyleClass().add("need-gap-y");

        //button handler navbar
        mainNavButton.setOnAction(s->{
            this.contentPanel.getChildren().setAll(new MainPart(user, this.context));
            selectNavButton(mainNavButton, navButtonsList);
            this.isSelected = "main";
        });

        ticketNavButton.setOnAction((s->{
            this.contentPanel.getChildren().setAll(new TicketPart(user, this.stage, this.context));
            selectNavButton(ticketNavButton, navButtonsList);
            this.isSelected = "tickets";
        }));
    }
    public void refresh(){
        this.contentPanel.getChildren().clear();
        if(this.user instanceof Admin){
              VBox activePart = switch (this.isSelected){
                case "compute" -> (VBox)new MachinesParts<Compute>(this.context, this.stage, Compute.class);
                case "gpu-compute" -> (VBox)new MachinesParts<GpuCompute>(this.context, this.stage, GpuCompute.class);
                case "storage" -> (VBox)new MachinesParts<Storage>(this.context, this.stage, Storage.class);
                case "network" -> (VBox)new MachinesParts<Network>(this.context, this.stage, Network.class);
                case "staff" -> (VBox)new StaffPart(this.stage, this.context);
                case "tickets" -> (VBox)new TicketPart((Admin)this.user, this.stage, this.context);
                default -> (VBox)new MainPart((Admin) this.user, this.context);
              };
              this.contentPanel.getChildren().add(activePart);
        }else{
            VBox activePart = switch (this.isSelected){
                case "tickets" -> (VBox)new TicketPart((Technician)this.user, this.stage, this.context);
                default -> (VBox)new MainPart((Technician)this.user, this.context);
            };
            this.contentPanel.getChildren().add(activePart);
        }
    }
    private static void selectNavButton(Button selected, List<Button> buttons) {
        buttons.forEach(b -> b.getStyleClass().remove("navbar-button-is-selected"));
        selected.getStyleClass().add("navbar-button-is-selected");
    }
}
