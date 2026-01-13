package application.components.machine;

import application.InterfaceMachineInfo;
import domain.Context;
import domain.Machine;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is used to create the buttons corresponding to the machines and to open the 
 * interface that provides access to machine information when the button is clicked
 * 
 * @author FIGUEIRAS Jossua
 */
public class MachineButton extends Button{
    private Context context;
    private Stage stage;
    private Machine machine;
    /** 
     * @param s the stage in which the machine info window will be shown
     * @param c the context containing machines and related data
     * @param m the machine represented by this button
     */
    public MachineButton(Stage s, Context c, Machine m){
        this.context = c;
        this.stage = s;
        this.machine = m;
        
        this.setAlignment(Pos.CENTER);
        this.setMinSize(200, 100);
        this.setMaxSize(200, 100);

        Label l = new Label(this.machine.getHostname());
        l.getStyleClass().add("bubble-title");
        Label ip = new Label(this.machine.getIpAddress());

        if(this.machine.getStatus().equals("Online")){
            this.getStyleClass().add("machine-bubble-normal");
            l.getStyleClass().add("Status-normal");
            ip.getStyleClass().add("Status-normal");
        }else if(this.machine.getStatus().equals("Maintenance")){
            this.getStyleClass().add("machine-bubble-warning");
            l.getStyleClass().add("Status-warning");
            ip.getStyleClass().add("Status-warning");
        }else if(this.machine.getStatus().equals("Offline")){
            this.getStyleClass().add("machine-bubble-error");
            l.getStyleClass().add("Status-error");
            ip.getStyleClass().add("Status-error");
        }
		VBox content = new VBox(l, ip);
		content.setAlignment(Pos.CENTER);
        this.setGraphic(content);

		this.setOnAction(button->{
			new InterfaceMachineInfo(this.stage, this.machine, this.context).show();;
		});
    }
}
