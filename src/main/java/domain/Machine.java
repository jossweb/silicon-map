package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.ComponentDao;
import Dao.MachineDao;

/**
 * Represents a machine in the system |
 * Représente une machine dans le système.
 *
 * Machines can be of various types (Compute, Storage, GpuCompute, Switch, Router, Firewall) |
 * Les machines peuvent être de différents types (Compute, Storage, GpuCompute, Switch, Router, Firewall).
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
     * Creates a Machine instance using explicit values |
     * Crée une instance de Machine à partir de valeurs explicites.
     *
     * @param id the unique identifier of the machine |
     * l'identifiant unique de la machine
     * @param hostname the hostname of the machine |
     * le nom d'hôte de la machine
     * @param ip_address the IP address of the machine |
     * l'adresse IP de la machine
     * @param mac_adress the MAC address of the machine |
     * l'adresse MAC de la machine
     * @param os the operating system installed |
     * le système d'exploitation installé
     * @param status the current status (Online/Offline) |
     * le statut actuel (En ligne/Hors ligne)
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
     * Creates a Machine instance from a SQL result set |
     * Crée une instance de Machine à partir d'un résultat SQL.
     *
     * @param sqlResult the SQL result set containing machine data |
     * le résultat SQL contenant les données de la machine
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
     * Returns the hostname of the machine |
     * Retourne le nom d'hôte de la machine.
	 * 
	 * @return hostname
     */
	public String getHostname() {
		return hostname;
	}
	/**
	 * Returns the IP address of the machine |
	 * Retourne l'adresse IP de la machine.
	 * 
	 * @return ipaddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
     * Returns the MAC address of the machine |
     * Retourne l'adresse MAC de la machine.
	 * 
	 * @return macAddress
     */
	public String getMacAdress() {
		return macAdress;
	}
	/**
     * Returns the operating system of the machine |
     * Retourne le système d'exploitation de la machine.
	 * 
	 * @return os
     */
	public String getOs() {
		return os;
	}
	/**
     * Returns the current status (Online/Offline) |
     * Retourne le statut actuel (En ligne/Hors ligne).
	 * 
	 * @return status
     */
	public String getStatus() {
		return status;
	}

    /**
     * Returns the unique identifier of the machine |
     * Retourne l'identifiant unique de la machine.
	 * 
	 * @return id
     */
	public int getId() {
		return id;
	}
	    /**
     * Returns the type of the machine based on its class |
     * Retourne le type de la machine selon sa classe.
     *
     * @param defaultType the default type to return if unknown |
     * le type par défaut à retourner si inconnu
     * @return the machine type as a string |
     * le type de machine sous forme de chaîne
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
     * Adds this machine to the database |
     * Ajoute cette machine à la base de données.
     */
	public void addInDB() {
		MachineDao.CreateMachineInDb(this);
	}
	/**
     * Updates this machine's data in the database |
     * Met à jour les données de cette machine dans la base de données.
     */
	public void modifyInDb() {
		MachineDao.updateMachineDb(this);
	}
	/**
     * Retrieves a machine by its ID |
     * Récupère une machine à partir de son identifiant.
     *
     * @param id the ID of the machine |
     *           l'identifiant de la machine
     * @return the Machine instance if found, otherwise null |
     *         l'instance de Machine si trouvée, sinon null
     */
	public static Machine getMachine(int id) {
		return MachineDao.getMachine(id);
	}
	/**
     * Deletes this machine from the database |
     * Supprime cette machine de la base de données.
     */
	public void deleteMachine() {
		MachineDao.deleteMachine(this);
	}
	 /**
     * Returns all components associated with this machine |
     * Retourne tous les composants associés à cette machine.
	 * 
	 * @return component list
     */
	public ArrayList<Component> getComponents(){
		return ComponentDao.getMachinesComponents(this.id);
	}
}
