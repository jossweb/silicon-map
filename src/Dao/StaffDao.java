package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Admin;
import domain.Staff;
import domain.Technician;

public abstract class StaffDao {
	public static Staff GetStaffMember(String username) {
		try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT staff.name, staff.first_name, staff.role, staff.hashpass, staff.available FROM staff WHERE staff.user_name=?;");
			stmt.setString(1, username);
			ResultSet result = stmt.executeQuery();
			
			if(result.getString("id")!=null) {
				if(result.getString("id")=="admin")
					return new Admin(result.getInt("id"), result.getString("name"), result.getString("first_name"), result.getString("hashpass"));
				else if(result.getString("id")=="technician")
					return new Technician(result.getInt("id"), result.getString("name"), result.getString("first_name"), result.getString("hashpass"));
			}
		} catch(SQLException e) {
			System.out.println("SQL ERROR !");
		}
		return null;
		
	}
}
