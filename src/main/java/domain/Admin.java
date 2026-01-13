package domain;

import java.sql.ResultSet;

import Dao.StaffDao;

/**
 * Represents an administrator user in the system 
 * 
 * @author FIGUEIRAS Jossua
 */

public class Admin extends Staff {
	/**
     * Creates an Admin instance using explicit values 
     *
     * @param id the unique identifier of the admin 
     * @param name the last name of the admin 
     * @param firstname the first name of the admin
     * @param hash the hashed password 
     * @param username the login username
     * @param available indicates if the admin account is active
     */
	public Admin(int id, String name, String firstname, String hash, String username, boolean available) {
		super(id, name, firstname, hash, username, available);
	}
     /**
     * Creates an Admin instance from a SQL result set
     *
     * @param sqlResult the SQL result set containing admin data
     */
	public Admin(ResultSet sqlResult){
		super(sqlResult);
	}
     /**
     * Retrieves an Admin by its unique identifier 
     *
     * @param id the ID of the admin to retrieve
     * @return the Admin instance if found and valid 
     * or null if the staff member is not an admin
     */
	public static Admin getAdminById(int id){
		Staff s = StaffDao.getStaffMember(id);
		if(s instanceof Admin){
			return (Admin)s;
		}else{
			return null;
		}
	}
}
