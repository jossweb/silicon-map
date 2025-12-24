package application.sections.admin;

import application.components.Navbar;
import domain.Admin;
import domain.Context;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminPart extends VBox{
    private Admin technician;
    private Stage stage;
    private Context context;

    public AdminPart(Admin t, Stage s, Context c){
        this.technician = t;
        this.stage = s;
        this.context = c;
        StackPane contentPanel = new StackPane();
        this.getChildren().addAll(new Navbar(this.technician , this.context, this.stage, contentPanel), contentPanel);
    }
}
