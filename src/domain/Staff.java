package domain;

import java.security.MessageDigest;

import org.mindrot.jbcrypt.BCrypt;

import dao.StaffDao;

public abstract class Staff {
	private int id;
	private String name;
	private String first_name;
	private String hashpass;
	
	public Staff(int id, String name, String firstname, String hashpass) {
		this.id = id;
		this.name = name;
		this.first_name = firstname;
		this.hashpass = hashpass;
	}
	public static String Hashpass(String pass) {
		return BCrypt.hashpw(pass, BCrypt.gensalt(12));
	}
	public static Staff CheckPass(String username, String pass) {
		//return the staff member if pass and username are correct 
		// else null
		Staff userFromDb = StaffDao.GetStaffMember(username);
		if(BCrypt.checkpw(pass, userFromDb.hashpass))
			return userFromDb;
		return null;
	}
}
