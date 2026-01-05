package domain;

import java.sql.ResultSet;

/**
 * Represents a compute machine this class specializes a Machine for computing purposes |
 * Représente une machine de type calcul (compute)
 * cette classe spécialise une Machine pour des usages de calcul.
 * 
 * @author FIGUEIRAS Jossua
 */

public class Compute extends Machine{
	/**
     * Creates a Compute instance using explicit values |
     * Crée une instance Compute à partir de valeurs explicites.
     *
     * @param id the unique identifier of the compute machine |
     * l'identifiant unique de la machine de calcul
     * @param hostname the network hostname |
     * le nom d'hôte réseau
     * @param ip_address the IP address |
     * l'adresse IP
     * @param mac_adress the MAC address |
     * l'adresse MAC
     * @param os the operating system |
     * le système d'exploitation
     * @param status the current machine status |
     * l'état actuel de la machine
     */
	public Compute(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	/**
     * Creates a Compute instance from a SQL result set |
     * Crée une instance Compute à partir d'un résultat SQL.
     *
     * @param sqlResult the SQL result set containing compute machine data |
     * le résultat SQL contenant les données de la machine
     */
	public Compute(ResultSet sqlResult){
		super(sqlResult);
	}
}
