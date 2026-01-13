package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a storage machine in the system
 * 
 * @author FIGUEIRAS Jossua
 */

public class Storage extends Machine{
	/**
     * Creates a Storage instance with explicit values
     *
     * @param id the unique identifier of the storage machine
     * @param hostname the hostname of the storage machine
     * @param ip_address the IP address of the storage machine
     * @param mac_adress the MAC address of the storage machine
     * @param os the operating system installed on the storage machine
     * @param status the current status of the machine
     */
	public Storage(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	/**
     * Creates a Storage instance from a SQL result set
     *
     * @param sqlResult the SQL result set containing storage machine data
     */
	public Storage(ResultSet sqlResult){
		super(sqlResult);
	}
	/**
     * Retrieves all Storage machines from a given Context
     *
     * @param s the Context containing a list of machines
     * @return an ArrayList of Storage machines
     */
	public static ArrayList<Storage> GetStorageFromStats(Context s){
		List<Storage> l =  s.getMachines().stream()
							.filter(e->e instanceof Storage)
							.map(e-> (Storage) e)
            				.toList();
		return new ArrayList<Storage>(l);
	}
}
