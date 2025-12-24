package application.sections.admin;

import application.InterfaceAddNewStaff;
import application.components.staff.StaffCard;
import domain.Context;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StaffPart extends VBox{
    public StaffPart(Stage stage, Context context){
        context.updateStaffMembersList();
        HBox head = new HBox();
        Label subTitle = new Label("Staff members");
        Button addStaffMember = new Button("+");
        Region midpart = new Region();
        HBox.setHgrow(midpart, Priority.ALWAYS);

        subTitle.getStyleClass().add("subsubtitle");
        addStaffMember.getStyleClass().add("addButton");

        head.getChildren().addAll(subTitle, midpart, addStaffMember);
        this.getChildren().addAll(head, new StaffCard(context));
    
        addStaffMember.setOnAction(e -> {
            InterfaceAddNewStaff form = new InterfaceAddNewStaff(stage);
            form.show();
        });
    }
}
