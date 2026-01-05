package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a storage machine in the system |
 * Représente une machine de stockage dans le système.
 * 
 * @author FIGUEIRAS Jossua
 */

public class Storage extends Machine{
	/**
     * Creates a Storage instance with explicit values |
     * Crée une instance Storage avec des valeurs explicites.
     *
     * @param id the unique identifier of the storage machine |
     * l'identifiant unique de la machine de stockage
     * @param hostname the hostname of the storage machine |
     * le nom d'hôte de la machine de stockage
     * @param ip_address the IP address of the storage machine |
     * l'adresse IP de la machine de stockage
     * @param mac_adress the MAC address of the storage machine |
     * l'adresse MAC de la machine de stockage
     * @param os the operating system installed on the storage machine |
     * le système d'exploitation installé sur la machine de stockage
     * @param status the current status of the machine |
     * le statut actuel de la machine
     */
	public Storage(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	/**
     * Creates a Storage instance from a SQL result set |
     * Crée une instance Storage à partir d'un résultat SQL.
     *
     * @param sqlResult the SQL result set containing storage machine data |
     * le résultat SQL contenant les données de la machine de stockage
     */
	public Storage(ResultSet sqlResult){
		super(sqlResult);
	}
	/**
     * Retrieves all Storage machines from a given Context |
     * Récupère toutes les machines de type Storage depuis un Context donné.
     *
     * @param s the Context containing a list of machines |
     * le Context contenant une liste de machines
     * @return an ArrayList of Storage machines |
     * un ArrayList de machines Storage
     */
	public static ArrayList<Storage> GetStorageFromStats(Context s){
		List<Storage> l =  s.getMachines().stream()
							.filter(e->e instanceof Storage)
							.map(e-> (Storage) e)
            				.toList();
		return new ArrayList<Storage>(l);
	}
}
