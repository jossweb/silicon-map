package application.sections;

import domain.Technician;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TechnicianPart extends VBox{
    private Technician technician;
    private Stage stage;

    public TechnicianPart(Technician t, Stage s){
        this.technician = t;
        this.stage = s;
    }
}
