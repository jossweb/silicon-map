package application.sections.admin;

import application.components.Navbar;
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
public class AdminPart extends VBox{
    private Admin technician;
    private Stage stage;
    private Context context;

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
        StackPane contentPanel = new StackPane();
        this.getChildren().addAll(new Navbar(this.technician , this.context, this.stage, contentPanel), contentPanel);
    }
}
