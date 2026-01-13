package domain;

import java.sql.ResultSet;

/**
 * Represents a Router in the network
 * 
 * @author FIGUEIRAS Jossua
 */

public class Router extends Network{
	/**
     * Creates a Router instance with explicit values
     *
     * @param id the unique identifier of the router
     * @param hostname the hostname of the router
     * @param ip_address the IP address of the router
     * @param mac_adress the MAC address of the router
     * @param os the operating system running on the router
     * @param status the current status of the router
     */
	public Router(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	/**
     * Creates a Router instance from a SQL result set
     *
     * @param sqlResult the SQL result set containing router data
     */
	public Router(ResultSet sqlResult){
		super(sqlResult);
	}
}
