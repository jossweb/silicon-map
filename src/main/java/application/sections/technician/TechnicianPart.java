package application.sections.technician;

import application.components.Navbar;
import application.interfaces.refreshable;
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
public class TechnicianPart extends VBox implements refreshable{
    private Technician technician;
    private Stage stage;
    private Context context;
    private StackPane contentPanel;

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
        this.contentPanel = new StackPane();
        this.getChildren().addAll(new Navbar(this.technician , this.context, this.stage, contentPanel));
        this.getChildren().add(contentPanel);
    }
    /**
     * refreshes interface. |
     * actualise l'interface.
     */
    public void refresh(){
        this.getChildren().clear();
        this.contentPanel = new StackPane();
        this.getChildren().addAll(new Navbar(this.technician , this.context, this.stage, contentPanel), contentPanel);
    }
}
