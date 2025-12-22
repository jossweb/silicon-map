package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.MachineDao;
import application.InterfaceMachineInfo;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class Machine {
	private int id;
	private String hostname;
	private String ip_address;
	private String mac_adress;
	private String os;
	private String status;
	
	public Machine(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		this.id = id;
		this.hostname=hostname;
		this.ip_address=ip_address;
		this.mac_adress=mac_adress;
		this.os=os;
		this.status=status;
	}
	public Machine(String hostname, String mac_adress, String os, String status) {
		this.hostname=hostname;
		this.ip_address=null;
		this.mac_adress=mac_adress;
		this.os=os;
		this.status=status;
	}
	public Machine(ResultSet sqlResult){
		try{
			this.id = sqlResult.getInt("id");
			this.hostname = sqlResult.getString("hostname");
			this.ip_address = sqlResult.getString("ip_address");
			this.mac_adress = sqlResult.getString("mac_address");
			this.os = sqlResult.getString("os");
			this.status = sqlResult.getString("status");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Machine with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	public String getHostname() {
		return hostname;
	}
	public String getIp_address() {
		return ip_address;
	}
	public String getMac_adress() {
		return mac_adress;
	}
	public String getOs() {
		return os;
	}
	public String getStatus() {
		return status;
	}
	public int getId() {
		return id;
	}
	public String whoami(String defaultType) {
		if(this instanceof Storage)
			return "Storage";
		else if(this instanceof Compute)
			return "Compute";
		else if(this instanceof GpuCompute)
			return "GpuCompute";
		else if(this instanceof Switch)
			return "Switch";
		else if(this instanceof Router)
			return "Router";
		else if(this instanceof Firewall)
			return "Firewall";
		else 
			return defaultType;
	}
	public void addInDB() {
		MachineDao.CreateMachineInDb(this);
	}
	public void modifyInDb() {
		MachineDao.updateMachineDb(this);
	}
	public static Machine getMachine(int id) {
		return MachineDao.getMachine(id);
	}
	public void deleteMachine() {
		MachineDao.deleteMachine(this);
	}
	public Button createMachineButton(Stage s, Context c){
        Button b = new Button();
        b.setAlignment(Pos.CENTER);
        b.setMinSize(200, 100);
        b.setMaxSize(200, 100);

        Label l = new Label(this.hostname);
        l.getStyleClass().add("bubble-title");
        Label ip = new Label(this.ip_address);

        if(this.status.equals("Online")){
            b.getStyleClass().add("machine-bubble-normal");
            l.getStyleClass().add("Status-normal");
            ip.getStyleClass().add("Status-normal");
        }else if(this.status.equals("Maintenance")){
            b.getStyleClass().add("machine-bubble-warning");
            l.getStyleClass().add("Status-warning");
            ip.getStyleClass().add("Status-warning");
        }else if(this.status.equals("Offline")){
            b.getStyleClass().add("machine-bubble-error");
            l.getStyleClass().add("Status-error");
            ip.getStyleClass().add("Status-error");
        }
		VBox content = new VBox(l, ip);
		content.setAlignment(Pos.CENTER);
        b.setGraphic(content);

		b.setOnAction(button->{
			new InterfaceMachineInfo(s, this, c).show();;
		});
        return b;
    }
}
