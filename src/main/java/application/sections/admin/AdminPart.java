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
    private Navbar nav;

    /**
     * Constructor for AdminPart
     *
     * @param t  The logged-in admin 
     * @param s  The current stage 
     * @param c  The application context 
     */
    public AdminPart(Admin t, Stage s, Context c){
        this.technician = t;
        this.stage = s;
        this.context = c;
        this.contentPanel = new StackPane();
        this.nav = new Navbar(this.technician , this.context, this.stage, contentPanel);
        this.getChildren().addAll(this.nav, contentPanel);
    }
    /**
     * refreshes interface 
     */
    public void refresh(){
        this.nav.refresh();
    }
}
