package application.sections.admin;

import java.util.ArrayList;

import application.components.machine.MachineButton;
import domain.Context;
import domain.Machine;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class MachinesParts<M extends Machine> extends VBox {
    private Context context;
    private Stage stage;

    public MachinesParts(Context context, Stage stage, Class<M> type) {
        this.context = context;
        this.stage = stage;

        this.context.updateMachinesList();

        ArrayList<M> machineList = new ArrayList<>();
        for (Machine m : this.context.getMachines()) {
            if (type.isInstance(m)) {
                machineList.add(type.cast(m));
            }
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        int row = 0;
        int col = 0;
        for (M m : machineList) {
            MachineButton machineButton = new MachineButton(this.stage, this.context, m);
            grid.add(machineButton, col, row);
            col++;
            if (col >= 5) {
                col = 0;
                row++;
            }
        }

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);  
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.getStyleClass().add("scroll-pane");

        this.getChildren().add(scrollPane);
    } 
}