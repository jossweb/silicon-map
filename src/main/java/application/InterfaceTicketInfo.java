package application;

import application.components.message.MessagePart;
import domain.Message;
import domain.Staff;
import domain.Ticket;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * An interface that allows you to display all the information about a ticket
 * 
 * @author FIGUEIRAS Jossua
 */
public class InterfaceTicketInfo extends Stage{
    private Stage stage;
    private Staff user;
    private Ticket ticket;
    /**
     * Constructs the ticket information interface
     * 
     * @param s the parent stage of the application
     * @param user the current staff user interacting with the ticket
     * @param ticket the ticket to display 
     */
    public InterfaceTicketInfo(Stage s, Staff user, Ticket ticket){
        this.stage = s;
        this.user = user;
        this.ticket = ticket;

        setTitle(this.ticket.getTitle() + " (" + this.ticket.getStatus() + ")");
        VBox root = new VBox(10);
        root.setId("main-pane");

        Scene scene = new Scene(root, 450, 600);
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        setScene(scene);

        initOwner(this.stage);
        initModality(Modality.WINDOW_MODAL);

        Label pageTitle = new Label(this.ticket.getTitle());
        pageTitle.getStyleClass().addAll("subtitle", "need-gap-y");

        Label description = new Label("Description : ");
        description.getStyleClass().add("bold");
        Label descriptionValue = new Label(this.ticket.getDescription());
        descriptionValue.setWrapText(true);
        HBox descriptionBox = new HBox(description, descriptionValue);

        Label machineName = new Label("Machine : ");
        machineName.getStyleClass().add("bold");
        Label hostnameValue = new Label(this.ticket.getMachine().getHostname());
        HBox machineBox = new HBox(machineName, hostnameValue);

        Label machineType = new Label("Type : ");
        machineType.getStyleClass().add("bold");
        Label machineTypeValue = new Label(this.ticket.getMachine().whoami("can't defined type"));
        HBox machineTypeBox = new HBox(machineType, machineTypeValue);

        Label ticketCreator = new Label("Ticket's creator : ");
        ticketCreator.getStyleClass().add("bold");
        Label ticketCreatorValue = new Label(this.ticket.getCreator().getName() + " " + this.ticket.getCreator().getFirst_name());
        HBox ticketCreatorBox = new HBox(ticketCreator, ticketCreatorValue);

        Label ticketTechnician = new Label("Ticket's technician : ");
        ticketTechnician.getStyleClass().add("bold");
        Label ticketTechnicianValue = new Label(this.ticket.getTechnician().getName() + " " + this.ticket.getTechnician().getFirst_name());
        HBox ticketTechnicianBox = new HBox(ticketTechnician, ticketTechnicianValue);


        MessagePart messagePart = new MessagePart(this.ticket, user);

        VBox.setVgrow(messagePart, Priority.ALWAYS);

        TextField newMessage = new TextField();

        Label errorLabel = new Label();
        errorLabel.setVisible(false);

        VBox newMessageBox = new VBox(5, new Label("Add message"), newMessage);

        HBox buttonBar = new HBox(5);
        Button addMessage = new Button("Send message");
        Button closeTicket = new Button("Close Ticket");
        addMessage.getStyleClass().add("submit-button");
        closeTicket.getStyleClass().addAll("submit-button", "red-button");
        buttonBar.getChildren().addAll(addMessage, closeTicket);

        root.getChildren().addAll(pageTitle, descriptionBox, machineBox, machineTypeBox, ticketCreatorBox, ticketTechnicianBox, messagePart, newMessageBox, buttonBar);

        addMessage.setOnAction(e->{
            String newContent = newMessage.getText();
            newMessage.setText("");
            if(!newContent.equals("")){
                new Message(this.user, this.ticket, newContent).updateInDb();
                messagePart.refresh();
            }else{
                errorLabel.setText("You can't send an empty message!");
                errorLabel.setVisible(true);
            }
        });
        closeTicket.setOnAction(e->{
            this.ticket.close();
            this.ticket.updateInDb();
            this.close();
        });

    }
}
