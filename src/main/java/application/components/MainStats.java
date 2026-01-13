package application.components;

import java.util.function.Function;

import domain.Context;
import domain.Ticket;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Main Section |
 * Section main
 * 
 * @author FIGUEIRAS Jossua
 */
public class MainStats extends HBox {
    private Context context;
    /**
     * @param c the Context object containing machines, tickets, and performance data
     */
    public MainStats(Context c){
        this.context = c;
        //update values
        this.context.updateLoad();
        this.context.updateTemp();

        Label statTitle = new Label("Global statistiques ");
		statTitle.getStyleClass().add("subsubtitle");
        double avgLoad = this.context.getAvgLoad();
        String loadStatus = "normal";
        if(avgLoad>100){
            loadStatus = "error";
        }else if(avgLoad > 75){
            loadStatus = "warning";
        }

        double avgTemp = this.context.getAvgTemp();
        String tempStatus = "normal";
        if(avgTemp>100){
            tempStatus = "error";
        }else if(avgTemp > 75){
            tempStatus = "warning";
        }

        this.setSpacing(10);

        this.context.updateMachinesList();
        int machineInDb = this.context.getMachines().size();
        String machineTitle = "Machine in db";
        if(machineInDb>1){
            machineTitle = "Machines in db";
        }

        this.context.updateTicketList();
        Function<Ticket, Boolean> isOpen = t -> t.getStatus().equals("open");
        int openTicketsCount = (int)this.context.getListTickets().stream()
                            .filter(isOpen::apply)
                            .count();
        String openTicketTitle = "Ticket open";
        if(openTicketsCount>1){
            openTicketTitle = "Tickets open";
        }

        String texteLoad = String.format("%.1f", avgLoad) + "%";
        String texteTemp = String.format("%.1f", avgTemp) + "°";

        this.getChildren().addAll(
            new StatCard(loadStatus, "Average load", texteLoad), 
            new StatCard(tempStatus, "Average temp", texteTemp),
            new StatCard("normal", machineTitle, String.valueOf(machineInDb)), 
            new StatCard("normal", openTicketTitle, String.valueOf(openTicketsCount))
        );
    }
}
