package domain;

import org.mindrot.jbcrypt.BCrypt;

import dao.StaffDao;

public abstract class Staff {
	private int id;
	private String name;
	private String first_name;
	private String user_name;
	private String hashpass;
	
	public Staff(int id, String name, String firstname, String hashpass, String username) {
		this.id = id;
		this.name = name;
		this.first_name = firstname;
		this.user_name = username;
		this.hashpass = hashpass;
	}
	public static String hashpass(String pass) {
		return BCrypt.hashpw(pass, BCrypt.gensalt(12));
	}
	public static Staff checkPass(String username, String pass) {
		//return the staff member if pass and username are correct 
		// else null
		Staff userFromDb = StaffDao.getStaffMember(username);
		if(BCrypt.checkpw(pass, userFromDb.hashpass))
			return userFromDb;
		return null;
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
	public void AddMemberToDb() {
		StaffDao.createStaffMember(this);
	}
	public void DeleteMemberInDb() {
		StaffDao.deleteStaffFromDb(this);
	}
}
