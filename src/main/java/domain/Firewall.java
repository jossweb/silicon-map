package domain;

import java.sql.ResultSet;

/**
 * Represents a firewall network machine |
 * Représente une machine réseau de type pare-feu.
 *
 * @author FIGUEIRAS Jossua
 */

public class Firewall extends Network{
	/**
     * Creates a Firewall instance using explicit values |
     * Crée une instance de Firewall à partir de valeurs explicites.
     *
     * @param id the unique identifier of the firewall |
     * l'identifiant unique du pare-feu
     * @param hostname the network hostname |
     * le nom réseau de la machine
     * @param ip_address the IP address of the firewall |
     * l'adresse IP du pare-feu
     * @param mac_adress the MAC address of the firewall |
     * l'adresse MAC du pare-feu
     * @param os the operating system |
     * le système d'exploitation
     * @param status the current status of the firewall |
     * l'état actuel du pare-feu
     */
	public Firewall(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	 /**
     * Creates a Firewall instance from a SQL result set |
     * Crée une instance de Firewall à partir d'un résultat SQL.
     *
     * @param sqlResult the SQL result set containing firewall data |
     * le résultat SQL contenant les données du pare-feu
     */
	public Firewall(ResultSet sqlResult){
		super(sqlResult);
	}
}
