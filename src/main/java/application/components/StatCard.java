package application.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Create a card for stats. |
 * Crée une carte pour afficher les 
 * stats.
 * 
 * @author FIGUEIRAS Jossua
 */
public class StatCard extends VBox{
    /**
    * @param status the status of the statistic | le statut de la statistique
    * @param labelContent the text label describing the statistic | le texte décrivant la statistique
    * @param ValueContent the value of the statistic to display | la valeur de la statistique à afficher
    */
    public StatCard(String status, String labelContent, String ValueContent){
        this.setSpacing(5);
        this.getStyleClass().add("stats"); 
        this.setAlignment(Pos.CENTER);
        HBox.setHgrow(this, Priority.ALWAYS);  
        this.setMaxWidth(Double.MAX_VALUE);

        Label num = new Label(ValueContent);
        num.getStyleClass().addAll("stats-num");
        Label label = new Label(labelContent);

        if(status.equals("warning")){
            num.getStyleClass().addAll("Status-warning");
            label.getStyleClass().addAll("Status-warning");
        }else if(status.equals("error")){
            num.getStyleClass().addAll("Status-error");
            label.getStyleClass().addAll("Status-error");
        }else if(status.equals("normal")){
                num.getStyleClass().addAll("Status-normal");
            label.getStyleClass().addAll("Status-normal");
        }
        this.getChildren().addAll(num, label);
    }
}
