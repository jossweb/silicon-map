package application;

import domain.Admin;
import domain.Staff;
import domain.Technician;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import type.RandomString;

/**
 * Interface for creating a new staff member 
 * 
 * @author FIGUEIRAS Jossua
 */
public class InterfaceAddNewStaff extends Stage {
    /**
     * Constructs the staff creation interface and sets up all UI elements 
     *
     * @param principalStage the main stage of the application
     */
    public InterfaceAddNewStaff(Stage principalStage){
        setTitle("Create new staff member");
        VBox root = new VBox(10);
        root.setId("main-pane");

        Label title = new Label("Create a new member");
        title.getStyleClass().addAll("subtitle", "need-gap-y");

        VBox namePart = new VBox(4);
        TextField nameField = new TextField();
        nameField.getStyleClass().add("userField");
        namePart.getChildren().addAll(new Label("Name"), nameField);

        VBox firstNamePart = new VBox(4);
        TextField firstNameField = new TextField();
        firstNameField.getStyleClass().add("userField");
        namePart.getChildren().addAll(new Label("First name"), firstNameField);

        Button submit = new Button("Create");
        submit.getStyleClass().add("submit-button");
        submit.setMaxWidth(Double.MAX_VALUE);

        HBox isAdminPart = new HBox();
        Label isAdminL = new Label("Is admin ");
        CheckBox checkBox = new CheckBox();
        isAdminPart.getChildren().addAll(isAdminL, checkBox);

        Label errorLabel = new Label();
        errorLabel.setVisible(false);

        root.getChildren().addAll(title, namePart, firstNamePart, isAdminPart, submit, errorLabel);

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        setScene(scene);

        initOwner(principalStage);
        initModality(Modality.WINDOW_MODAL);

        submit.setOnAction(e->{

            String name = nameField.getText();
            String firstname = firstNameField.getText();
            if(name == "" || firstname == ""){
                errorLabel.setText("Please fill in the fields");
                errorLabel.setVisible(true);
            }else{
                String newUserName = new RandomString(32).getString();
                String newPassword = new RandomString(32).getString();
                Staff newMember;
                if(checkBox.isSelected()){
                    newMember = new Admin(0, name, firstname, Staff.hashpass(newPassword), newUserName, false);
                }else{
                    newMember = new Technician(0, name, firstname, Staff.hashpass(newPassword), newUserName, false);
                }
                newMember.AddMemberToDb();

                VBox successRoot = new VBox(10);
                successRoot.setPadding(new Insets(20));
                successRoot.setAlignment(Pos.CENTER);

                Label successLabel = new Label("Success");
                successLabel.getStyleClass().add("subtitle");

                HBox newUserId = new HBox(10);
                Label newUserIdLabel = new Label("username : ");
                TextArea newUserIdValue = new TextArea(newUserName);
                newUserIdValue.getStyleClass().add("text-area");
                newUserIdValue.setEditable(false);
                newUserIdValue.setWrapText(true);

                HBox newUserPass = new HBox(10);
                Label newUserPassLabel = new Label("password : ");
                TextArea newUserPassValue = new TextArea(newPassword);
                newUserPassValue.getStyleClass().add("text-area");
                newUserPassValue.setEditable(false);
                newUserPassValue.setWrapText(true);

                HBox.setHgrow(newUserIdValue, Priority.NEVER);
                HBox.setHgrow(newUserPassValue, Priority.NEVER);

    
                newUserIdValue.setMinWidth(280);
                newUserIdValue.setMaxWidth(280);
                
                newUserIdValue.setMinHeight(30);
                newUserIdValue.setMaxHeight(30);

                newUserPassValue.setMinWidth(280);
                newUserPassValue.setMaxWidth(280);

                newUserPassValue.setMinHeight(30);
                newUserPassValue.setMaxHeight(30);

                newUserPass.getChildren().addAll(newUserPassLabel, newUserPassValue);
                newUserId.getChildren().addAll(newUserIdLabel, newUserIdValue);

                Label warning = new Label("Warning: Keep these warnings safe, it will be impossible to retrieve them!");
                warning.setWrapText(true);

                Button close = new Button("Close");
                close.getStyleClass().add("submit-button");
                close.setMaxWidth(Double.MAX_VALUE);
                close.setOnAction(f -> close());

                successRoot.getChildren().addAll(successLabel, newUserId, newUserPass, warning, close);
                getScene().setRoot(successRoot);
            }
        });        
    }
}
