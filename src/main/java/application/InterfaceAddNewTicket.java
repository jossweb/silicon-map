package application;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import domain.Admin;
import domain.Component;
import domain.Machine;
import domain.Staff;
import domain.Context;
import domain.Technician;
import domain.Ticket;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * New ticket creation interface 
 * 
 * @author FIGUEIRAS Jossua
 */
public class InterfaceAddNewTicket extends Stage {

    private int selectedTechnicianId;
    private int selectedMachineId;
    Set<Integer> selectedComponentsIds; 
    private Context stats;

    /**
     * Constructs the new ticket creation interface
     * 
     * @param principalStage the main stage of the application
     * @param s the context containing machines, components and staff lists
     * @param userLogin the admin creating the ticket
     */
    public InterfaceAddNewTicket(Stage principalStage, Context s, Admin userLogin){
        //init with default value (when value < 0 technician is not selected by user)
        this.selectedTechnicianId = -1;
        this.selectedMachineId = -1;
        this.selectedComponentsIds = new HashSet<Integer>();
        this.stats = s;

        setTitle("Add ticket");
        VBox root = new VBox(10);
        root.setId("main-pane");

        Label title = new Label("Create a new ticket");
        title.getStyleClass().addAll("subtitle", "need-gap-y");


        TextField titleField = new TextField();
        titleField.getStyleClass().add("userField");
        VBox titlePart = new VBox(4, new Label("Title"), titleField);

        TextField descriptionField = new TextField();
        descriptionField.getStyleClass().add("userField");
        VBox descriptionPart = new VBox(4, new Label("Description"), descriptionField);

        ScrollPane technicianSelector = createHorizontalTecchnicianSelector();
        VBox technicianSelectorBox = new VBox(4, new Label("Technician"), technicianSelector);

        ScrollPane machineSelector = createHorizontalMachineSelector();
        VBox MachineSelectorBox = new VBox(4, new Label("Machine"), machineSelector);

        ScrollPane ComponentsSelectorSelector = createHorizontalComponentSelector();
        VBox ComponentsSelectorBox = new VBox(4, new Label("Add more components"), ComponentsSelectorSelector);

        Button submit = new Button("Create");
        submit.getStyleClass().add("submit-button");
        submit.setMaxWidth(Double.MAX_VALUE);

        Label errorLabel = new Label();
        errorLabel.setVisible(false);


        root.getChildren().addAll(title, titlePart, descriptionPart, technicianSelectorBox,MachineSelectorBox, ComponentsSelectorBox, submit, errorLabel);

        Scene scene = new Scene(root, 600, 650);
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        setScene(scene);

        initOwner(principalStage);
        initModality(Modality.WINDOW_MODAL);

        submit.setOnAction(e->{
            String newTitle = titleField.getText();
            String newDescription = descriptionField.getText();
            if(!(newTitle.compareTo("")==1)){
                if (!(this.selectedTechnicianId==-1)){
                    Ticket t = new Ticket(0, Machine.getMachine(selectedMachineId), userLogin, (Technician)Staff.getUserById(this.selectedTechnicianId), newTitle, newDescription, "Open", LocalDateTime.now(), null);
                    t.createTicket();
                    for(int cId : this.selectedComponentsIds){
                        Component c = Component.getById(cId);
                        c.setTicketId(t.getId());
                        c.updateComponent();
                    }
                    VBox successRoot = new VBox(10);
                    successRoot.setPadding(new Insets(20));
                    successRoot.setAlignment(Pos.CENTER);

                    Label successLabel = new Label("Success");
                    successLabel.getStyleClass().add("subtitle");

                    Label explainationLabel = new Label("Ticket add successfully");
                    explainationLabel.getStyleClass().add("subsubtitle");

                    Button close = new Button("Close");
                    close.getStyleClass().add("submit-button");
                    close.setMaxWidth(Double.MAX_VALUE);
                    close.setOnAction(f -> close());

                    successRoot.getChildren().addAll(successLabel, explainationLabel, close);
                    getScene().setRoot(successRoot);

                }else{
                    errorLabel.setText("You need to select a technician for this ticket");
                    errorLabel.setVisible(true);
                }
            }else{
                errorLabel.setText("You can't give an empty title");
                errorLabel.setVisible(true);
            }
        });        
    }
    /**
     * Horizontal selector that allows adding an additional component to the ticket
     */
    private ScrollPane createHorizontalComponentSelector(){
        this.stats.updateComponentList();

        HBox box = new HBox();
        box.setSpacing(10);
        box.setFillHeight(true);

        if(this.stats.getListComponents().size()>0){
                    
            for (Component c : this.stats.getListComponents()) {
                if(c.getMachineId() == 0 && c.getTicketId() == 0){
                    Button b = new Button(c.getBrand() + " " + c.getModel());
                    b.getStyleClass().add("selectors-button");
                    b.setUserData(c.getId());

                    b.setOnAction(e -> {
                        if(this.selectedComponentsIds.contains((int) b.getUserData()) ){
                            this.selectedComponentsIds.remove((int) b.getUserData());
                            b.getStyleClass().remove("selectors-button-is-selected");
                        }else{
                            b.getStyleClass().add("selectors-button-is-selected");
                            this.selectedComponentsIds.add((int) b.getUserData());
                        }
                    });

                box.getChildren().add(b);
                }
            }
        }else{
            box.getChildren().add(new Label("No component available!"));
        }

        ScrollPane scrollPane = new ScrollPane(box);
        scrollPane.setPrefHeight(80);
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.getStyleClass().add("nobg1");

        return scrollPane;

    }
    /**
     * Horizontal selector for adding a machine to the ticket
     */
    private ScrollPane createHorizontalMachineSelector(){
        this.stats.updateMachinesList();

        HBox box = new HBox();
        box.setSpacing(10);
        box.setFillHeight(true);

        for (Machine m : this.stats.getMachines()) {
                Button b = new Button(m.getHostname() + " " + m.getIpAddress());
                b.getStyleClass().add("selectors-button");
                b.setUserData(m.getId());

                b.setOnAction(e -> {
                    box.getChildren().forEach(btn ->
                        btn.getStyleClass().remove("selectors-button-is-selected")
                    );
                    if((int) b.getUserData() == this.selectedMachineId){
                        this.selectedMachineId = -1;
                    }else{
                        b.getStyleClass().add("selectors-button-is-selected");
                        this.selectedMachineId = (int) b.getUserData();
                    }
                });

                box.getChildren().add(b);
        }

        ScrollPane scrollPane = new ScrollPane(box);
        scrollPane.setPrefHeight(80);
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.getStyleClass().add("nobg1");

        return scrollPane;

    }
    /**
     * Horizontal selector for adding a technician to the ticket
     */
    private ScrollPane createHorizontalTecchnicianSelector() {

        this.stats.updateStaffMembersList();

        HBox box = new HBox();
        box.setSpacing(10);
        box.setFillHeight(true);

        for (Staff s : this.stats.getListStaffMembers()) {

            if (s instanceof Technician) {

                Technician t = (Technician)s;

                Button b = new Button(t.getName() + " " + t.getFirst_name());
                b.getStyleClass().add("selectors-button");
                b.setUserData(t.getId());

                b.setOnAction(e -> {
                    box.getChildren().forEach(btn ->
                        btn.getStyleClass().remove("selectors-button-is-selected")
                    );
                    if((int) b.getUserData() == this.selectedTechnicianId){
                        //nobody selected
                        this.selectedTechnicianId = -1;
                    }else{
                        b.getStyleClass().add("selectors-button-is-selected");
                        this.selectedTechnicianId = (int) b.getUserData();
                    }
                });

                box.getChildren().add(b);
            }
        }

        ScrollPane scrollPane = new ScrollPane(box);
        scrollPane.setPrefHeight(80);
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.getStyleClass().add("nobg1");

        return scrollPane;
    }
}