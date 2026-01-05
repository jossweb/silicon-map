package application.sections.admin;

import java.util.ArrayList;

import application.components.machine.MachineButton;
import domain.Context;
import domain.Machine;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Displays a machine dashboard of the type passed as a parameter |
 * Affiche un dashboard de machine du type passé en paramètre.
 * 
 * @author FIGUEIRAS Jossua
 * 
 * @param <M> the type of machine to display
 */
public class MachinesParts<M extends Machine> extends VBox {
    private Context context;
    private Stage stage;

    /**
     * 
     * @param context the application context containing the list of machines. |
     *                le contexte de l’application contenant la liste des machines.
     * @param stage the main application window. |
     *              la fenêtre principale de l’application.
     * @param type the class of the machine type to display (e.g., Compute, GpuCompute, Storage, Network). |
     *             la classe du type de machine à afficher (ex : Compute, GpuCompute, Storage, Network).
     */
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