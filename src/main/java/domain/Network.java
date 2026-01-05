package domain;

import java.sql.ResultSet;

/**
 * Represents a network machine (Switch, Router, Firewall, etc.) |
 * Représente une machine réseau (Switch, Router, Firewall, etc.).
 * 
 * @author FIGUEIRAS Jossua
 */

public abstract class Network extends Machine{
	/**
     * Creates a Network instance with explicit values |
     * Crée une instance Network avec des valeurs explicites.
     *
     * @param id the unique identifier of the machine |
     * l'identifiant unique de la machine
     * @param hostname the hostname of the network machine |
     * le nom d'hôte de la machine réseau
     * @param ip_address the IP address of the machine |
     * l'adresse IP de la machine
     * @param mac_adress the MAC address of the machine |
     * l'adresse MAC de la machine
     * @param os the operating system of the machine |
     * le système d'exploitation de la machine
     * @param status the status of the machine (Online, Offline, etc.) |
     * le statut de la machine (En ligne, Hors ligne, etc.)
     */
    public Network(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	/**
     * Creates a Network instance from a SQL result set |
     * Crée une instance Network à partir d'un résultat SQL.
     *
     * @param sqlResult the SQL result set containing network machine data |
     * le résultat SQL contenant les données de la machine réseau
     */
	public Network(ResultSet sqlResult){
		super(sqlResult);
	}
}
