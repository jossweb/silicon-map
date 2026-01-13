package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.ComponentDao;
import Dao.MachineDao;

/**
 * Represents a machine in the system 
 *
 * Machines can be of various types (Compute, Storage, GpuCompute, Switch, Router, Firewall) 
 * 
 * @author FIGUEIRAS Jossua
 */

public abstract class Machine{
	private int id;
	private String hostname;
	private String ipAddress;
	private String macAdress;
	private String os;
	private String status;
	/**
     * Creates a Machine instance using explicit values
     *
     * @param id the unique identifier of the machine 
     * @param hostname the hostname of the machine 
     * @param ip_address the IP address of the machine 
     * @param mac_adress the MAC address of the machine
     * @param os the operating system installed
     * @param status the current status (Online/Offline)
     */
	public Machine(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		this.id = id;
		this.hostname=hostname;
		this.ipAddress=ip_address;
		this.macAdress=mac_adress;
		this.os=os;
		this.status=status;
	}
	/**
     * Creates a Machine instance from a SQL result set
     *
     * @param sqlResult the SQL result set containing machine data
     */
	public Machine(ResultSet sqlResult){
		try{
			this.id = sqlResult.getInt("id");
			this.hostname = sqlResult.getString("hostname");
			this.ipAddress = sqlResult.getString("ip_address");
			this.macAdress = sqlResult.getString("mac_address");
			this.os = sqlResult.getString("os");
			this.status = sqlResult.getString("status");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Machine with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	/**
     * Returns the hostname of the machine
	 * 
	 * @return hostname
     */
	public String getHostname() {
		return hostname;
	}
	/**
	 * Returns the IP address of the machine
	 * 
	 * @return ipaddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
     * Returns the MAC address of the machine
	 * 
	 * @return macAddress
     */
	public String getMacAdress() {
		return macAdress;
	}
	/**
     * Returns the operating system of the machine
	 * 
	 * @return os
     */
	public String getOs() {
		return os;
	}
	/**
     * Returns the current status (Online/Offline)
	 * 
	 * @return status
     */
	public String getStatus() {
		return status;
	}

    /**
     * Returns the unique identifier of the machine
	 * 
	 * @return id
     */
	public int getId() {
		return id;
	}
	    /**
     * Returns the type of the machine based on its class
     *
     * @param defaultType the default type to return if unknown
     * @return the machine type as a string
     */
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
	/**
     * Adds this machine to the database 
     */
	public void addInDB() {
		MachineDao.CreateMachineInDb(this);
	}
	/**
     * Updates this machine's data in the database 
     */
	public void modifyInDb() {
		MachineDao.updateMachineDb(this);
	}
	/**
     * Retrieves a machine by its ID 
     *
     * @param id the ID of the machine 
     * @return the Machine instance if found, otherwise null 
     */
	public static Machine getMachine(int id) {
		return MachineDao.getMachine(id);
	}
	/**
     * Deletes this machine from the database 
     */
	public void deleteMachine() {
		MachineDao.deleteMachine(this);
	}
	 /**
     * Returns all components associated with this machine 
	 * 
	 * @return component list
     */
	public ArrayList<Component> getComponents(){
		return ComponentDao.getMachinesComponents(this.id);
	}
}
