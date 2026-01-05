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
     * Creates a GpuCompute instance using explicit values |
     * Crée une instance de GpuCompute à partir de valeurs explicites.
     *
     * @param id the unique identifier of the machine |
     * l'identifiant unique de la machine
     * @param hostname the hostname of the machine |
     * le nom d'hôte de la machine
     * @param ip_address the IP address of the machine |
     * l'adresse IP de la machine
     * @param mac_adress the MAC address of the machine |
     * l'adresse MAC de la machine
     * @param os the operating system installed |
     * le système d'exploitation installé
     * @param status the current status (Online/Offline) |
     * le statut actuel (En ligne/Hors ligne)
     */
	public GpuCompute(int id,String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	/**
     * Creates a GpuCompute instance from a SQL result set |
     * Crée une instance de GpuCompute à partir d'un résultat SQL.
     *
     * @param sqlResult the SQL result set containing machine data |
     * le résultat SQL contenant les données de la machine
     */
	public GpuCompute(ResultSet sqlResult){
		super(sqlResult);
	}
}
