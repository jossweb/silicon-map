package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.ComponentDao;

public abstract class Component {
	private int id;
	private String brand;
	private String model;
	private int machineId;
	private int ticketId;
	
	public Component(int id, String brand, String model, int mid, int tid) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.machineId = mid;
		this.ticketId = tid;
	}
	public Component(ResultSet result){
		try{
			this.id = result.getInt("id");
			this.brand = result.getString("brand");
			this.model = result.getString("model");
			this.machineId = result.getInt("machine_id");
			this.ticketId = result.getInt("ticket");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Component with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	public int getId() {
		return id;
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public int getMachineId() {
		return machineId;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public void updateComponent(){
		ComponentDao.update(this);
	}
	public String whoami(String alternative){
		if(this instanceof Chassis)
			return "Chassis";
		else if(this instanceof Cpu)
			return "Cpu";
		else if(this instanceof Disk)
			return "Disk";
		else if(this instanceof Gpu)
			return "Gpu";
		else if(this instanceof Ram)
			return "Ram";
		else if(this instanceof PowerSupply)
			return "Power Supply";
		else 
			return alternative;
	}	
	public static Component getById(int id){
		return ComponentDao.getComponentById(id);
	}
	public static boolean compareComponentById(Component c1, Component c2){
		if(c1.getId() == c2.getId()){
			return true;
		}else{
			return false;
		}
	}
}
