package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Admin;
import domain.Staff;
import domain.Technician;

public abstract class StaffDao {
	public static Staff getStaffMember(String username) {
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT staff.name, staff.first_name, staff.role, staff.hashpass, staff.available FROM staff WHERE staff.user_name=?;");
			stmt.setString(1, username);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				if(result.getString("id")=="admin")
					return new Admin(result.getInt("id"), result.getString("name"), result.getString("first_name"), result.getString("hashpass"), result.getString("user_name"));
				else if(result.getString("id")=="technician")
					return new Technician(result.getInt("id"), result.getString("name"), result.getString("first_name"), result.getString("hashpass"), result.getString("user_name"));
			}
		} catch(SQLException e) {
			System.out.println("SQL ERROR ! /n explains :" + e);
		}
		return null;
		
	}
	public static Staff getStaffMember(int id) {
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT staff.name, staff.first_name, staff.role, staff.hashpass, staff.available FROM staff WHERE staff.id=?;");
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				if(result.getString("id")=="admin")
					return new Admin(result.getInt("id"), result.getString("name"), result.getString("first_name"), result.getString("hashpass"), result.getString("user_name"));
				else if(result.getString("id")=="technician")
					return new Technician(result.getInt("id"), result.getString("name"), result.getString("first_name"), result.getString("hashpass"), result.getString("user_name"));
			}
		} catch(SQLException e) {
			System.out.println("SQL ERROR ! /n explains :" + e);
		}
		return null;
		
	}
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
				return "Erreur : impossible de créer ce nouveau membre";
		}catch(java.sql.SQLIntegrityConstraintViolationException e){
			return "Erreur : ce username est déjà utilisé par un autre membre";
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
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
				return "Mis à jour !";
			else
				return "Erreur : impossible de modifier ce membre";
			
		}catch(java.sql.SQLIntegrityConstraintViolationException e){
			return "Erreur : ce username est déjà utilisé par un autre membre";
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
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
}
