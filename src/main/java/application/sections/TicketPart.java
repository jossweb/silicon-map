package application.sections;

import application.InterfaceAddNewTicket;
import domain.Admin;
import domain.Context;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicketPart extends VBox{
    private Stage stage;
    private Context context;
    public TicketPart(Admin admin, Stage stage, Context context){
        this.stage= stage;
        this.context = context;
        //top bar
        HBox head = new HBox();
        Label subTitle = new Label("All tickets");
        Button addStaffMember = new Button("+");
        Region midpart = new Region();
        HBox.setHgrow(midpart, Priority.ALWAYS);
        subTitle.getStyleClass().add("subsubtitle");
        addStaffMember.getStyleClass().add("addButton");

        head.getChildren().addAll(subTitle, midpart, addStaffMember);
        this.getChildren().addAll(head, new TicketSection(this.context));

        addStaffMember.setOnAction(e -> {
            InterfaceAddNewTicket form = new InterfaceAddNewTicket(this.stage, this.context, admin);
            form.show();
        });
    }
}
