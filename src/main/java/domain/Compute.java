package domain;

import java.sql.ResultSet;

/**
 * Represents a compute machine this class specializes a Machine for computing purposes
 * 
 * @author FIGUEIRAS Jossua
 */

public class Compute extends Machine{
	/**
     * Creates a Compute instance using explicit values
     *
     * @param id the unique identifier of the compute machine
     * @param hostname the network hostname
     * @param ip_address the IP address
     * @param mac_adress the MAC address
     * @param os the operating system
     * @param status the current machine status
     */
	public Compute(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	/**
     * Creates a Compute instance from a SQL result set
     *
     * @param sqlResult the SQL result set containing compute machine data
     */
	public Compute(ResultSet sqlResult){
		super(sqlResult);
	}
}
