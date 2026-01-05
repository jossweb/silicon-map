package application.sections;

import application.InterfaceAddNewTicket;
import domain.Admin;
import domain.Context;
import domain.Technician;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * ticket part |
 * Affichage des tickets.
 * 
 * @author FIGUEIRAS Jossua
 */
public class TicketPart extends VBox{
    private Stage stage;
    private Context context;
    /**
     * Admin view for ticket management. | Vue Admin pour la gestion des tickets.
     * 
     * Displays all tickets and allows creating new tickets. |
     * Affiche tous les tickets et permet la création de nouveaux tickets.
     * 
     * @param admin the logged-in admin | l'admin connecté
     * @param stage the parent stage | la fenêtre parente
     * @param context the system context containing tickets and machine data | le contexte système contenant les tickets et les machines
     */
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
        this.getChildren().addAll(head, new TicketSection(this.context, stage, admin));

        addStaffMember.setOnAction(e -> {
            InterfaceAddNewTicket form = new InterfaceAddNewTicket(this.stage, this.context, admin);
            form.show();
        });
    }
    /**
     * Technician version
     * 
     * @param technician the logged-in technican | technicien connecté
     * @param stage the parent stage | la fenêtre parente
     * @param context the system context containing tickets and machine data | le contexte système contenant les tickets et les machines
     */
    public TicketPart(Technician technician, Stage stage, Context context){
        this.stage= stage;
        this.context = context;
        //top bar
        HBox head = new HBox();
        Label subTitle = new Label("All tickets");
        subTitle.getStyleClass().add("subsubtitle");

        head.getChildren().addAll(subTitle);
        this.getChildren().addAll(head, new TicketSection(this.context, stage, technician));
    }
}
