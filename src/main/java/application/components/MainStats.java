package application.components;

import domain.Context;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MainStats extends HBox {
    private Context context;
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
        int openTicketsCount = (int)this.context.getListTickets().stream()
                            .filter(e->e.getStatus().equals("open"))
                            .count();
        String openTicketTitle = "Ticket open";
        if(openTicketsCount>1){
            openTicketTitle = "Tickets open";
        }
        
        String flecheLoad = "";
        java.util.List<type.Tuple<Integer, java.time.LocalDateTime>> listLoad = this.context.getAvgLoadLastInputs();

        if (listLoad.size() >= 2) {
            int actuel = listLoad.get(listLoad.size() - 1).getFirst();
            int précédent = listLoad.get(listLoad.size() - 2).getFirst();
            
            if (actuel > précédent) flecheLoad = "↗";
            else if (actuel < précédent) flecheLoad = "↘";
            else if (actuel == précédent) flecheLoad = "=";
        }

        String flecheTemp = "";
        java.util.List<type.Tuple<Integer, java.time.LocalDateTime>> listTemp = this.context.getAvgTempLastInputs();

        if (listTemp.size() >= 2) {
            int actuel = listTemp.get(listTemp.size() - 1).getFirst();
            int précédent = listTemp.get(listTemp.size() - 2).getFirst();
            
            if (actuel > précédent) flecheTemp = "↗";
            else if (actuel < précédent) flecheTemp = "↘";
            else if (actuel == précédent) flecheTemp = "=";
        }

        String texteLoad = String.format("%.1f", avgLoad) + "%" + flecheLoad;
        String texteTemp = String.format("%.1f", avgTemp) + "°" + flecheTemp;

        this.getChildren().addAll(
            new StatCard(loadStatus, "Average load", texteLoad), 
            new StatCard(tempStatus, "Average temp", texteTemp), 
            new StatCard("normal", machineTitle, String.valueOf(machineInDb)), 
            new StatCard("normal", openTicketTitle, String.valueOf(openTicketsCount))
        );
    }
}
