package domain;

import java.sql.ResultSet;

/**
 * Represents a GPU-enabled compute machine |
 * Représente une machine de calcul spécialisé sur les GPU.
 *
 * @author FIGUEIRAS Jossua
 */

public class GpuCompute extends Machine{
	/**
     * Creates a GpuCompute instance using explicit values
     *
     * @param id the unique identifier of the machine 
     * @param hostname the hostname of the machine
     * @param ip_address the IP address of the machine
     * @param mac_adress the MAC address of the machine
     * @param os the operating system installed
     * @param status the current status (Online/Offline)
     */
	public GpuCompute(int id,String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	/**
     * Creates a GpuCompute instance from a SQL result set 
     *
     * @param sqlResult the SQL result set containing machine data
     */
	public GpuCompute(ResultSet sqlResult){
		super(sqlResult);
	}
}
