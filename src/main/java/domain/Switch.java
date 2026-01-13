package domain;

import java.sql.ResultSet;
	/**
	 * Represents a network switch in the system 
	 * 
	 * @author FIGUEIRAS Jossua
	 */

public class Switch extends Network{
	/**
     * Creates a Switch instance with explicit values
     *
     * @param id the unique identifier of the switch
     * @param hostname the hostname of the switch
     * @param ip_address the IP address of the switch
     * @param mac_adress the MAC address of the switch
     * @param os the operating system installed on the switch 
     * @param status the current status of the switch
     */
	public Switch(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	/**
     * Creates a Switch instance from a SQL result set
     *
     * @param sqlResult the SQL result set containing switch data
     */
	public Switch(ResultSet sqlResult){
		super(sqlResult);
	}
}
