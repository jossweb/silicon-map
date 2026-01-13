package domain;

import java.sql.ResultSet;

/**
 * Represents a network machine (Switch, Router, Firewall, etc.)
 * 
 * @author FIGUEIRAS Jossua
 */

public abstract class Network extends Machine{
	/**
     * Creates a Network instance with explicit values
     *
     * @param id the unique identifier of the machine 
     * @param hostname the hostname of the network machine 
     * @param ip_address the IP address of the machine 
     * @param mac_adress the MAC address of the machine
     * @param os the operating system of the machine
     * @param status the status of the machine (Online, Offline, etc.)
     */
    public Network(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	/**
     * Creates a Network instance from a SQL result set
     *
     * @param sqlResult the SQL result set containing network machine data
     */
	public Network(ResultSet sqlResult){
		super(sqlResult);
	}
}
