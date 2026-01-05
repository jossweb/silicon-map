package application.components;

import java.util.List;

import application.sections.MainPart;
import application.sections.TicketPart;
import application.sections.admin.MachinesParts;
import application.sections.admin.StaffPart;
import domain.Admin;
import domain.Compute;
import domain.Context;
import domain.GpuCompute;
import domain.Network;
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
import javafx.stage.Stage;

/**
 * Navbar of principal stage. |
 * Barre de navigation du stage principal.
 * 
 * @author FIGUEIRAS Jossua
 */
public class Navbar extends GridPane{
    private Context context;
    private Stage stage;
    private StackPane contentPanel;
    /**
     * Admin version. |
     * Version admin.
     * 
     * @param user the Admin currently logged in
     * @param context the Context object containing machines, tickets, and other data
     * @param stage the Stage on which the interface is displayed
     * @param contentPanel the StackPane where the content of each section is displayed
     */
    public Navbar(Admin user, Context context, Stage stage, StackPane contentPanel){

        this.context = context;
        this.stage = stage;
        this.contentPanel = contentPanel;

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
        });

        computeNavButton.setOnAction(s->{
            this.contentPanel.getChildren().setAll(new MachinesParts<Compute>(this.context, this.stage, Compute.class));
            selectNavButton(computeNavButton, navButtonsList);
        });

        gpuComputeNavButton.setOnAction(s->{
            this.contentPanel.getChildren().setAll(new MachinesParts<GpuCompute>(this.context, this.stage, GpuCompute.class));
            selectNavButton(gpuComputeNavButton, navButtonsList);
        });

        storageNavButton.setOnAction(s->{
            this.contentPanel.getChildren().setAll(new MachinesParts<Storage>(this.context, this.stage, Storage.class));
            selectNavButton(storageNavButton, navButtonsList);
        });

        networkNavButton.setOnAction(s->{
            this.contentPanel.getChildren().setAll(new MachinesParts<Network>(this.context, this.stage, Network.class));
            selectNavButton(networkNavButton, navButtonsList);
        });
        staffnavButton.setOnAction(s->{
            this.contentPanel.getChildren().setAll(new StaffPart(this.stage, this.context));
            selectNavButton(staffnavButton, navButtonsList);
        });
        ticketNavButton.setOnAction((s->{
            this.contentPanel.getChildren().setAll(new TicketPart(user, this.stage, this.context));
            selectNavButton(ticketNavButton, navButtonsList);
        }));
    }
    /**
     * Technician version. |
     * Version technicien.
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
        });

        ticketNavButton.setOnAction((s->{
            this.contentPanel.getChildren().setAll(new TicketPart(user, this.stage, this.context));
            selectNavButton(ticketNavButton, navButtonsList);
        }));
    }
    private static void selectNavButton(Button selected, List<Button> buttons) {
        buttons.forEach(b -> b.getStyleClass().remove("navbar-button-is-selected"));
        selected.getStyleClass().add("navbar-button-is-selected");
    }
}
