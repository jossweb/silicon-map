package application.sections;

import application.components.Charts;
import application.components.MainStats;
import domain.Admin;
import domain.Context;
import domain.Technician;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * main part |
 * Page d'accueil.
 * 
 * @author FIGUEIRAS Jossua
 */
public class MainPart extends VBox {
    private Context context;
    /**
     * Constructor for Admin users
     * 
     * @param user the logged-in admin 
     * @param context the system context containing machine and ticket data
     */
    public MainPart(Admin user, Context context){
        this.context = context;

        MainStats stats = new MainStats(context);
        // Charts part
        HBox chartsPart = new HBox(5);
        LineChart<String, Number> chart1 = new Charts(this.context.getAvgTempLastInputs(), "Temperature °");
        LineChart<String, Number> chart2 = new Charts(this.context.getAvgLoadLastInputs(), "Load %");

        chartsPart.getChildren().addAll(chart1, chart2);

        Label evolutionLabel = new Label("Evolution");
        evolutionLabel.getStyleClass().addAll("subsubtitle", "need-gap-y");

        this.getChildren().addAll(stats, evolutionLabel, chartsPart);
    }
    /**
     * Constructor for Technician users 
     * 
     * @param user the logged-in technician
     * @param context the system context containing and ticket
     */
    public MainPart(Technician user, Context context){
        this.context = context;

        MainStats stats = new MainStats(context);
        // Charts part
        HBox chartsPart = new HBox(5);
        LineChart<String, Number> chart1 = new Charts(this.context.getAvgTempLastInputs(), "Temperature °");
        LineChart<String, Number> chart2 = new Charts(this.context.getAvgLoadLastInputs(), "Load %");

        chartsPart.getChildren().addAll(chart1, chart2);

        Label evolutionLabel = new Label("Evolution");
        evolutionLabel.getStyleClass().addAll("subsubtitle", "need-gap-y");

        this.getChildren().addAll(stats, evolutionLabel, chartsPart);
    }
}
