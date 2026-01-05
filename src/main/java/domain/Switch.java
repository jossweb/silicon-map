package domain;

import java.sql.ResultSet;
	/**
	 * Represents a network switch in the system |
	 * Représente un switch réseau dans le système.
	 * 
	 * @author FIGUEIRAS Jossua
	 */

public class Switch extends Network{
	/**
     * Creates a Switch instance with explicit values |
     * Crée une instance Switch avec des valeurs explicites.
     *
     * @param id the unique identifier of the switch |
     * l'identifiant unique du switch
     * @param hostname the hostname of the switch |
     * le nom d'hôte du switch
     * @param ip_address the IP address of the switch |
     * l'adresse IP du switch
     * @param mac_adress the MAC address of the switch |
     * l'adresse MAC du switch
     * @param os the operating system installed on the switch |
     * le système d'exploitation installé sur le switch
     * @param status the current status of the switch |
     * le statut actuel du switch
     */
	public Switch(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	/**
     * Creates a Switch instance from a SQL result set |
     * Crée une instance Switch à partir d'un résultat SQL.
     *
     * @param sqlResult the SQL result set containing switch data |
     * le résultat SQL contenant les données du switch
     */
	public Switch(ResultSet sqlResult){
		super(sqlResult);
	}
}
