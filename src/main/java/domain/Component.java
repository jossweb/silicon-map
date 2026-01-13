package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.ComponentDao;

/**
 * Represents a generic hardware component
 * This abstract class is the base for all specific components
 * 
 * @author FIGUEIRAS Jossua
 */

public abstract class Component {
	private int id;
	private String brand;
	private String model;
	private int machineId;
	private int ticketId;
	
	/**
     * Creates a Component instance using explicit values
     *
     * @param id the unique identifier of the component
     * @param brand the manufacturer brand 
     * @param model the model reference
     * @param mid the identifier of the associated machine
     * @param tid the identifier of the associated ticket
     */
	public Component(int id, String brand, String model, int mid, int tid) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.machineId = mid;
		this.ticketId = tid;
	}
	/**
     * Creates a Component instance from a SQL result set
     *
     * @param result the SQL result set containing component data
     */
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
	/**
     * Returns the unique identifier of the component
     *
     * @return the component ID
     */
	public int getId() {
		return id;
	}
	/**
     * Returns the manufacturer brand
     *
     * @return the brand name
     */
	public String getBrand() {
		return brand;
	}
	/**
     * Returns the model reference
     *
     * @return the model name
     */
	public String getModel() {
		return model;
	}
	/**
     * Returns the associated machine identifier
     *
     * @return the machine ID
     */
	public int getMachineId() {
		return machineId;
	}
	/**
     * Returns the associated ticket identifier
     *
     * @return the ticket ID
     */
	public int getTicketId() {
		return ticketId;
	}
	/**
     * Updates the associated ticket identifier
     *
     * @param ticketId the new ticket identifier
     */
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	/**
     * Persists the component updates to the database
    */
	public void updateComponent(){
		ComponentDao.update(this);
	}
	/**
     * Returns the component type as a string
     *
     * @param alternative the fallback value if the type is unknown
     * @return the component type name
     */
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
	/**
     * Retrieves a component by its unique identifier
     *
     * @param id the ID of the component to retrieve
     * @return the component instance if found
     */
	public static Component getById(int id){
		return ComponentDao.getComponentById(id);
	}
	/**
     * Compares two components based on their identifiers
     *
     * @param c1 the first component 
     * @param c2 the second component
     * @return true if both components have the same ID
     */
	public static boolean compareComponentById(Component c1, Component c2){
		if(c1.getId() == c2.getId()){
			return true;
		}else{
			return false;
		}
	}
}
