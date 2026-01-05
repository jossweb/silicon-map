package domain;

import java.sql.ResultSet;

/**
 * Represents a Router in the network |
 * Représente un routeur dans le réseau.
 * 
 * @author FIGUEIRAS Jossua
 */

public class Router extends Network{
	/**
     * Creates a Router instance with explicit values |
     * Crée une instance Router avec des valeurs explicites.
     *
     * @param id the unique identifier of the router |
     * l'identifiant unique du routeur
     * @param hostname the hostname of the router |
     * le nom d'hôte du routeur
     * @param ip_address the IP address of the router |
     * l'adresse IP du routeur
     * @param mac_adress the MAC address of the router |
     * l'adresse MAC du routeur
     * @param os the operating system running on the router |
     * le système d'exploitation installé sur le routeur
     * @param status the current status of the router |
     * le statut actuel du routeur
     */
	public Router(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	/**
     * Creates a Router instance from a SQL result set |
     * Crée une instance Router à partir d'un résultat SQL.
     *
     * @param sqlResult the SQL result set containing router data |
     * le résultat SQL contenant les données du routeur
     */
	public Router(ResultSet sqlResult){
		super(sqlResult);
	}
}
