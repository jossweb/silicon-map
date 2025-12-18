package domain;

import java.sql.ResultSet;

import Dao.StaffDao;

public class Admin extends Staff {
	public Admin(int id, String name, String firstname, String hash, String username, boolean available) {
		super(id, name, firstname, hash, username, available);
	}
	public Admin(ResultSet sqlResult){
		super(sqlResult);
	}
	public static Admin getAdminById(int id){
		Staff s = StaffDao.getStaffMember(id);
		if(s instanceof Admin){
			return (Admin)s;
		}else{
			return null;
		}
	}
}
