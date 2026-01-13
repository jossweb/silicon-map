package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import Dao.StaffDao;
import error.LoginError;
import error.UserNotFound;
import error.BadPassword;

/**
 * Represents a staff member of the system 
 * 
 * @author FIGUEIRAS Jossua
 */

public abstract class Staff {
	private int id;
	private String name;
	private String first_name;
	private String user_name;
	private String hashpass;
	private boolean available;
	
	/**
     * Creates a Staff instance with explicit values
     *
     * @param id the unique identifier of the staff member
     * @param name the last name of the staff member 
     * @param firstname the first name of the staff member
     * @param hashpass the hashed password
     * @param username the login username
     * @param available indicates if the account is active
     */
	public Staff(int id, String name, String firstname, String hashpass, String username, boolean available) {
		this.id = id;
		this.name = name;
		this.first_name = firstname;
		this.user_name = username;
		this.hashpass = hashpass;
		this.available = available;
	}
	 /**
     * Creates a Staff instance from a SQL result set 
     *
     * @param sqlResult the SQL result set containing staff data
     */
	public Staff(ResultSet sqlResult){
		try{
			this.id = sqlResult.getInt("id");
			this.name = sqlResult.getString("name");
			this.first_name = sqlResult.getString("first_name");
			this.user_name = sqlResult.getString("user_name");
			this.hashpass = sqlResult.getString("hashpass");
			this.available = sqlResult.getBoolean("available");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Staff with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}

	}
	/**
     * Hashes a plain password using BCrypt
     *
     * @param pass the plain password
     * 
	 * @return the hashed password
     */
	public static String hashpass(String pass) {
		return BCrypt.hashpw(pass, BCrypt.gensalt(12));
	}
	/**
     * Checks login credentials and returns the Staff member
     *
     * @param username the login username
     * @param pass the plain password
     * @return the Staff instance if credentials are correct
     * @throws LoginError if login fails (user not found or bad password)
     */
	public static Staff checkPass(String username, String pass) throws LoginError{
		//return the staff member if pass and username are correct 
		// else null
		Staff userFromDb = StaffDao.getStaffMember(username);
		if(userFromDb != null){
			if(BCrypt.checkpw(pass, userFromDb.hashpass))
				return userFromDb;

			throw new BadPassword();
		}
		throw new UserNotFound();
	}
	public String getName() {
		return name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getHashpass() {
		return hashpass;
	}
	public int getId() {
		return id;
	}

    /**
     * Adds the staff member to the database
     */
	public void AddMemberToDb() {
		StaffDao.createStaffMember(this);
	}
	/**
     * Deletes the staff member from the database
     */
	public void DeleteMemberInDb() {
		StaffDao.deleteStaffFromDb(this);
	}
	/**
     * Updates the availability status of the staff member
     *
     * @param bool true if available, false otherwise
     */
	public void setAvailable(boolean bool){
		StaffDao.changeStatus(bool, this.id);
	}
	public boolean getAvailable(){
		return this.available;
	}
	/**
     * Retrieves a staff member by its ID 
     *
     * @param id the unique identifier of the staff member
     * 
	 * @return the Staff instance
     */
	public static Staff getUserById(int id){
		return StaffDao.getStaffMember(id);
	}
}
