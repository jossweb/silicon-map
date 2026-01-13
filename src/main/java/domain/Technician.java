package domain;

import java.sql.ResultSet;

import Dao.StaffDao;

/**
 * Represents a technician user in the system
 * 
 * @author FIGUEIRAS Jossua
 */

public class Technician extends Staff{
    /**
     * Creates a Technician instance using explicit values 
     *
     * @param id the unique identifier of the technician
     * @param name the last name of the technician 
     * @param firstname the first name of the technician
     * @param hash the hashed password
     * @param username the login username
     * @param available indicates if the technician account is active
     */
	public Technician(int id, String name, String firstname, String hash, String username, boolean available) {
		super(id, name, firstname, hash, username, available);
	}
	 /**
     * Creates a Technician instance from a SQL result set
     *
     * @param sqlResult the SQL result set containing technician data 
     */
	public Technician(ResultSet sqlResult){
		super(sqlResult);
	}

    /**
     * Retrieves a Technician by its unique identifier
     *
     * @param id the ID of the technician to retrieve
     * @return the Technician instance if found and valid,
     * or null if the staff member is not a technician
     */
	public static Technician getTechncianById(int id){
		Staff s = StaffDao.getStaffMember(id);
		if(s instanceof Technician){
			return (Technician)s;
		}else{
			return null;
		}
	}
}
