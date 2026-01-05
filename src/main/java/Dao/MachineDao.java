package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Compute;
import domain.Firewall;
import domain.GpuCompute;
import domain.Machine;
import domain.Router;
import domain.Storage;
import domain.Switch;

/**
 * All SQL interactions related to the machines |
 * Toutes les interactions SQL liées aux machines.
 * 
 * @author FIGUEIRAS Jossua
 */
public abstract class MachineDao {
	/**
	 * Create a machine in the database |
	 * Crée une machine dans la db.
	 * 
	 * @param m the machine that will be saved |
	 *  La machine qui sera sauvegardée
	 * 
	 * @return true if machine saved successfully |
	 * true si la machine a réussi à enregistrer.
	 */
	public static boolean CreateMachineInDb(Machine m) {
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO machines (hostname, ip_address, mac_address, os, status, type) VALUES (?, ?, ?, ?, ?, ?);");
			stmt.setString(1, m.getHostname());
			stmt.setString(2, m.getIpAddress());
			stmt.setString(3, m.getMacAdress());
			stmt.setString(4, m.getOs());
			switch (m.getStatus()) {
				case "Online" : stmt.setString(5, "Online");
				case "Maintenance" : stmt.setString(5, "Maintenance");
				default : stmt.setString(5, "Offline");
			}
			stmt.setString(6, m.whoami("Compute"));
			
			return stmt.executeUpdate()==1;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Create a machine in the database |
	 * Crée une machine dans la db.
	 * 
	 * @param id the ID of the machine to retrieve |
	 * l'identifiant de la machine à récupérer
	 * 
	 * @return The machine recovered. |
	 * La machine récupéré.
	 */
	public static Machine getMachine(int id) {
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT id, hostname, ip_address, mac_address, os, status, type FROM machines WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				switch (result.getString("type")) {
					case "Compute" : return new Compute(result);
					case "Storage" : return new Storage(result);
					case "GPU_Compute" : return new GpuCompute(result);
					case "switch" : return new Switch(result);
					case "router" : return new Router(result);
					case "firewall" : return new Firewall(result);
					default : return null;
				}
			}
			return null;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Get all machine from db |
	 * récupère toutes les machines depuis la base de données
	 * 
	 * @return The array list that contain all machine from db |
	 * Un array list qui contient tout les machines venant de la db
	 */
	public static ArrayList<Machine> getAllMachines(){
		ArrayList<Machine> list = new ArrayList<Machine>();
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT id, hostname, ip_address, mac_address, os, status, type FROM machines");
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				switch (result.getString("type")) {
					case "Compute" : list.add(new Compute(result));
					break;
					case "Storage" : list.add(new Storage(result));
					break;
					case "GPU_Compute" : list.add(new GpuCompute(result));
					break;
					case "switch" : list.add(new Switch(result));
					break;
					case "router" : list.add(new Router(result));
					break;
					case "firewall" : list.add(new Firewall(result));
					break;
					default : return null;
				}
			}
			return list;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Update machine in database |
	 * met à jour une machine en db
	 * 
	 * @param m the update machine |
	 * la machine à mettre à jour
	 * 
	 * @return Status in string |
	 * Status de la requête dans une chaine de caractères
	 */
	public static String updateMachineDb(Machine m) {
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("UPDATE machines SET hostname = ?, ip_address = ?, mac_address = ?, os = ?, status = ?, type = ? WHERE id = ?;");
			stmt.setString(1, m.getHostname());
			stmt.setString(2, m.getIpAddress());
			stmt.setString(3, m.getMacAdress());
			stmt.setString(4, m.getOs());
			switch (m.getStatus()) {
				case "Online" : stmt.setString(5, "Online");
				case "Maintenance" : stmt.setString(5, "Maintenance");
				default : stmt.setString(5, "Offline");
			}
			stmt.setString(6, m.whoami("Compute"));
			stmt.setInt(7, m.getId());
			
			if(stmt.executeUpdate()==1)
				return "Mis à jour";
			return "Erreur impossible de mettre à jour cette machine";
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Delete machine in database |
	 * Supprime une machine en db
	 * 
	 * @param m the delete machine |
	 * la machine à supprimer
	 * 
	 * @return true if machine delete|
	 * true si la machine est supprimé.
	 */
	public static boolean deleteMachine(Machine m) {
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM machines WHERE id = ?;");
			stmt.setInt(1, m.getId());
			
			if(stmt.executeUpdate()==1) 
				return true;
			else
				return false;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
