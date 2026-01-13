package domain;

import java.sql.ResultSet;

/**
 * Represents a firewall network machine
 *
 * @author FIGUEIRAS Jossua
 */

public class Firewall extends Network{
	/**
     * Creates a Firewall instance using explicit values
     *
     * @param id the unique identifier of the firewall 
     * @param hostname the network hostname
     * @param ip_address the IP address of the firewall
     * @param mac_adress the MAC address of the firewall 
     * @param os the operating system 
     * @param status the current status of the firewall 
     */
	public Firewall(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	 /**
     * Creates a Firewall instance from a SQL result set
     *
     * @param sqlResult the SQL result set containing firewall data
     */
	public Firewall(ResultSet sqlResult){
		super(sqlResult);
	}
}
