package application.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import type.Tuple;

public class Charts extends LineChart<String, Number>{
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
