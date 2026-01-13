package application.sections.admin;

import application.components.Navbar;
import application.interfaces.refreshable;
import domain.Admin;
import domain.Context;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Admin part |
 * Partie administrateur.
 * 
 * @author FIGUEIRAS Jossua
 */
public class AdminPart extends VBox implements refreshable{
    private Admin technician;
    private Stage stage;
    private Context context;
    private StackPane contentPanel;

    /**
     * Constructor for AdminPart |
     * Constructeur de la partie interface pour un administrateur.
     *
     * @param t  The logged-in admin | L'administrateur connecté
     * @param s  The current stage | La fenêtre principale
     * @param c  The application context | Le contexte de l'application
     */
    public AdminPart(Admin t, Stage s, Context c){
        this.technician = t;
        this.stage = s;
        this.context = c;
        this.contentPanel = new StackPane();
        this.getChildren().addAll(new Navbar(this.technician , this.context, this.stage, contentPanel), contentPanel);
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
