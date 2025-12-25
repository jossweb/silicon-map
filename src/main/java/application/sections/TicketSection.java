package application.sections;

import application.components.ticket.TicketCard;
import domain.Context;
import domain.Technician;
import domain.Ticket;
import javafx.geometry.HPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class TicketSection extends ScrollPane{
    public TicketSection(Context context){
        context.updateTicketList();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        ColumnConstraints firstCol = new ColumnConstraints();
        firstCol.setHgrow(Priority.ALWAYS);

        ColumnConstraints secondCol = new ColumnConstraints();
        secondCol.setHalignment(HPos.CENTER);

        ColumnConstraints thirdCol = new ColumnConstraints();
        thirdCol.setHgrow(Priority.ALWAYS);

        ColumnConstraints fourthCol = new ColumnConstraints();
        fourthCol.setHgrow(Priority.ALWAYS);

        grid.getColumnConstraints().addAll(firstCol, secondCol, thirdCol, fourthCol);

        int col = 0;
        int row = 0;
        for(int i = 0; i < context.getListTickets().size(); i++){
            grid.add(new TicketCard(context.getListTickets().get(i)), col, row);
            if(col == 3){
                col = 0;
                row ++;
            }else{
                col ++;
            }
        }
        this.setContent(grid);
        this.setFitToWidth(true);  
        this.setFitToHeight(true);
        this.setPannable(true);
        this.getStyleClass().add("scroll-pane");
    }
    public TicketSection(Context context, Technician technician){
        context.updateTicketList();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        ColumnConstraints firstCol = new ColumnConstraints();
        firstCol.setHgrow(Priority.ALWAYS);

        ColumnConstraints secondCol = new ColumnConstraints();
        secondCol.setHalignment(HPos.CENTER);

        ColumnConstraints thirdCol = new ColumnConstraints();
        thirdCol.setHgrow(Priority.ALWAYS);

        ColumnConstraints fourthCol = new ColumnConstraints();
        fourthCol.setHgrow(Priority.ALWAYS);

        grid.getColumnConstraints().addAll(firstCol, secondCol, thirdCol, fourthCol);

        int col = 0;
        int row = 0;
        for(int i = 0; i < context.getListTickets().size(); i++){
            Ticket t = context.getListTickets().get(i);
            if(t.getStatus().equals(("open")) && t.getTechnician().getId() == technician.getId()){
                grid.add(new TicketCard(t), col, row);
                if(col == 3){
                    col = 0;
                    row ++;
                }else{
                    col ++;
                }
            }
        }
        this.setContent(grid);
        this.setFitToWidth(true);  
        this.setFitToHeight(true);
        this.setPannable(true);
        this.getStyleClass().add("scroll-pane");
    }
}
