package application;

import java.util.HashSet;
import java.util.Set;

import domain.Component;
import domain.Machine;
import domain.Staff;
import domain.Statistics;
import domain.Technician;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InterfaceAddNewTicket extends Stage {

    private int selectedTechnicianId;
    private int selectedMachineId;
    Set<Integer> selectedComponentsIds; 
    private Statistics stats;

    public InterfaceAddNewTicket(Stage principalStage, Statistics s){

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
            System.out.print("clicked");
        });        
    }
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
    private ScrollPane createHorizontalMachineSelector(){
        this.stats.updateMachinesList();

        HBox box = new HBox();
        box.setSpacing(10);
        box.setFillHeight(true);

        for (Machine m : this.stats.getMachines()) {
                Button b = new Button(m.getHostname() + " " + m.getIp_address());
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