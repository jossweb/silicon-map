package application.sections.technician;

import application.components.Navbar;
import domain.Context;
import domain.Technician;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Technician part |
 * Partie technicien.
 * 
 * @author FIGUEIRAS Jossua
 */
public class TechnicianPart extends VBox{
    private Technician technician;
    private Stage stage;
    private Context context;

    /**
     * Constructor for TechnicianPart |
     * Constructeur de la partie interface pour un technicien.
     *
     * @param t  The logged-in technician | Le technicien connecté
     * @param s  The current stage | La fenêtre principale
     * @param c  The application context (data) | Le contexte de l'application (données)
     */
    public TechnicianPart(Technician t, Stage s, Context c){
        this.technician = t;
        this.stage = s;
        this.context = c;
        StackPane contentPanel = new StackPane();
        this.getChildren().addAll(new Navbar(this.technician , this.context, this.stage, contentPanel));
        this.getChildren().add(contentPanel);
    }
}
