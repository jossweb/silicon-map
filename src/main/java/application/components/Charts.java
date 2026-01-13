package application.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import type.Tuple;

/**
 * Creating a chart based on the data passed as parameters
 * 
 * @author FIGUEIRAS Jossua
 */

public class Charts extends LineChart<String, Number>{
    /**
    * -
    * @param values the list of tuples containing the integer value and its corresponding timestamp
    * @param name the name of the series displayed on the chart
    */
    public Charts(ArrayList<Tuple<Integer, LocalDateTime>> values, String name) {
        super(new CategoryAxis(), new NumberAxis());

        NumberAxis yAxis = new NumberAxis();
        yAxis.setTickUnit(1);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(name);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        values.forEach(t -> {
            series.getData().add(new XYChart.Data<>(t.getSecond().format(formatter), t.getFirst()));
        });

        this.getData().add(series);
        this.setCreateSymbols(false);
        this.setPrefHeight(350);  
        this.getStyleClass().add("need-gap-y");
    }
}
