package application.sections;

import application.components.ticket.TicketCard;
import domain.Admin;
import domain.Context;
import domain.Technician;
import domain.Ticket;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicketSection extends ScrollPane {

    public TicketSection(Context context, Stage stage, Admin admin) {
        context.updateTicketList();
        
        if (context.getListTickets().isEmpty()) {
            afficherMessageVide();
        } else {
            GridPane grid = creerGridDeBase();
            int col = 0;
            int row = 0;
            for (int i = 0; i < context.getListTickets().size(); i++) {
                grid.add(new TicketCard(context.getListTickets().get(i), admin, stage), col, row);
                if (col == 3) {
                    col = 0;
                    row++;
                } else {
                    col++;
                }
            }
            configurerAffichage(grid);
        }
    }

    public TicketSection(Context context, Stage stage, Technician technician) {
        context.updateTicketList();
        
        GridPane grid = creerGridDeBase();
        int col = 0;
        int row = 0;
        int ticketAffiche = 0;

        for (int i = 0; i < context.getListTickets().size(); i++) {
            Ticket t = context.getListTickets().get(i);
            if (t.getStatus().equals("open") && t.getTechnician().getId() == technician.getId()) {
                grid.add(new TicketCard(t, technician, stage), col, row);
                ticketAffiche++;
                if (col == 3) {
                    col = 0;
                    row++;
                } else {
                    col++;
                }
            }
        }

        if (ticketAffiche == 0) {
            afficherMessageVide();
        } else {
            configurerAffichage(grid);
        }
    }

    private void afficherMessageVide() {
        VBox emptyBox = new VBox();
        emptyBox.setAlignment(Pos.CENTER);
        Label message = new Label("No tickets opened");
        message.getStyleClass().add("subsubtitle");
        emptyBox.getChildren().add(message);
        
        this.setContent(emptyBox);
        this.setFitToHeight(true);
        this.setFitToWidth(true);
    }

    private GridPane creerGridDeBase() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        for (int i = 0; i < 4; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setHgrow(Priority.ALWAYS);
            if (i == 1) col.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(col);
        }
        return grid;
    }

    private void configurerAffichage(GridPane grid) {
        this.setContent(grid);
        this.setFitToWidth(true);
        this.setFitToHeight(true);
        this.setPannable(true);
        this.getStyleClass().add("scroll-pane");
    }
}