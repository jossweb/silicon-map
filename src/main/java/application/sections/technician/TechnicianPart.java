package application.sections.technician;

import application.components.Navbar;
import domain.Context;
import domain.Technician;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TechnicianPart extends VBox{
    private Technician technician;
    private Stage stage;
    private Context context;

    public TechnicianPart(Technician t, Stage s, Context c){
        this.technician = t;
        this.stage = s;
        this.context = c;
        StackPane contentPanel = new StackPane();
        this.getChildren().addAll(new Navbar(this.technician , this.context, this.stage, contentPanel));
        this.getChildren().add(contentPanel);
    }
}
