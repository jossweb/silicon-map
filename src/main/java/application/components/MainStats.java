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
        this.getChildren().addAll(new StatCard(loadStatus, "Average load", String.format("%.1f", avgLoad) + "%"), new StatCard(tempStatus, "Average temp", String.format("%.1f", avgTemp) + "°"), new StatCard("error", "test", "0%"), new StatCard(" ", "test", "0%"));
    }
}
