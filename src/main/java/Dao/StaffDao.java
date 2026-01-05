package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Admin;
import domain.Staff;
import domain.Technician;

/**
 * All SQL interactions related to the staff |
 * Toutes les interactions SQL liées aux staff.
 * 
 * @author FIGUEIRAS Jossua
 */
public abstract class StaffDao {
	/**
	 * Get staff member by username |
	 * récupère le staff member avec l'username.
	 * 
	 * @param username staff's username | l'username du staff member
	 * 
	 * @return Staff
	 */
	public static Staff getStaffMember(String username) {
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT staff.id, staff.name, staff.first_name, staff.role, staff.hashpass, staff.available, staff.user_name FROM staff WHERE staff.user_name=?;");
			stmt.setString(1, username);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				if(result.getString("role").equals("admin"))
					return new Admin(result);
				else if(result.getString("role").equals("technician"))
					return new Technician(result);
			}
		} catch(SQLException e) {
			System.out.println("SQL ERROR ! /n explains :" + e);
		}
		return null;
		
	}
	/**
	 * Get staff member by id |
	 * récupère le staff member avec l'id.
	 * 
	 * @param id staff's id | l'id du staff member
	 * 
	 * @return Staff
	 */
	public static Staff getStaffMember(int id) {
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT staff.id, staff.name, staff.user_name, staff.first_name, staff.role, staff.hashpass, staff.available FROM staff WHERE staff.id=?;");
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				if(result.getString("role").equals("admin")){
					return new Admin(result);
				}
				else if(result.getString("role").equals("technician")){
					return new Technician(result);
				}
			}
		} catch(SQLException e) {
			System.out.println("SQL ERROR ! /n explains :" + e);
		}
		return null;
		
	}
	/**
	 * Get all staff member |
	 * récupère tout les staff member.
	 * 
	 * @return Arraylist who contain Staff members | un arraylist qui contient tout les staff members
	 */
	public static ArrayList<Staff> getAllStaffMembers(){
		try {
			ArrayList<Staff> membersList = new ArrayList<Staff>();
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT staff.id, staff.user_name, staff.name, staff.first_name, staff.role, staff.hashpass, staff.available FROM staff");
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				if(result.getString("role").equals("admin")){
					membersList.add(new Admin(result));
				}
				else if(result.getString("role").equals("technician")){
					membersList.add(new Technician(result));
				}
			}
			return membersList;
		} catch(SQLException e) {
			System.out.println("ERROR : Sql error /n explains :" + e);
		}
		return null;
	}
	/**
	 * Create the staff member in db |
	 * Crée le staff member dans la db.
	 * 
	 * @param s the staff who will be saved|
	 * le staff member qui va être sauvegardé
	 * 
	 * @return Status string | 
	 * le status dans une chaine de caractère.
	 */
	public static String createStaffMember(Staff s) {
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO staff (name, first_name, user_name, role, available, hashpass) VALUES (?, ?, ?, ?, ?, ?);");
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getFirst_name());
			stmt.setString(3, s.getUser_name());
			if(s instanceof Admin) 
				stmt.setString(4, "Admin");
			else
				stmt.setString(4, "Technician");
			
			stmt.setInt(5, 0);
			stmt.setString(6, s.getHashpass());
			
			if(stmt.executeUpdate()==1) 
				return "Créé avec succès";
			else
				return "Error : can't create this member";
		}catch(java.sql.SQLIntegrityConstraintViolationException e){
			return "Error : username already used";
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Update the staff member in db |
	 * Met à jour le staff member dans la db.
	 * 
	 * @param s the staff who will be update|
	 * le staff member qui va être mis à jour.
	 * 
	 * @return Status string | 
	 * le status dans une chaine de caractère.
	 */
	public static String updateStaffMember(Staff s) {
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("UPDATE staff SET name = ?, first_name = ?, user_name = ?, role = ?, available = ?, hashpass = ? WHERE id = ?;");
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getFirst_name());
			stmt.setString(3, s.getUser_name());
			if(s instanceof Admin) 
				stmt.setString(4, "Admin");
			else
				stmt.setString(4, "Technician");
			
			stmt.setInt(5, 0);
			stmt.setString(6, s.getHashpass());
			stmt.setInt(7, s.getId());
			
			if(stmt.executeUpdate()==1) 
				return "Update !";
			else
				return "Error : can't update in db";
			
		}catch(java.sql.SQLIntegrityConstraintViolationException e){
			return "Error : username already used";
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Delete the staff member in db |
	 * Supprime le staff member dans la db.
	 * 
	 * @param s he staff who will be delete|
	 * le staff member qui va être supprimé.
	 * 
	 * @return true if delete successfully| 
	 * true si le staff member est supprimé avec succès.
	 */
	public static boolean deleteStaffFromDb(Staff s) {
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM staff WHERE id = ?;");
			stmt.setInt(1, s.getId());
			
			if(stmt.executeUpdate()==1) 
				return true;
			else
				return false;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Update the staff member's status in db |
	 * Met à jour le status du staff members.
	 * 
	 * @param status true if member is online |
	 * true si le membre est en ligne.
	 * @param id member id |
	 * id du membre
	 * 
	 */
	public static void changeStatus(boolean status, int id){
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("UPDATE staff SET available = ? WHERE id = ?;");
			if(status){
				stmt.setInt(1, 1);
			}else{
				stmt.setInt(1, 0);
			}
			stmt.setInt(2, id);
			
			if(stmt.executeUpdate()!=1){
				throw new RuntimeException("error");
			}
			
		}catch(SQLException e) {
			System.out.print(e);
		}
	}
}
