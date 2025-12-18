package domain;

import java.sql.ResultSet;

import Dao.StaffDao;

public class Technician extends Staff{
	public Technician(int id, String name, String firstname, String hash, String username, boolean available) {
		super(id, name, firstname, hash, username, available);
	}
	public Technician(ResultSet sqlResult){
		super(sqlResult);
	}
	public static Technician getTechncianById(int id){
		Staff s = StaffDao.getStaffMember(id);
		if(s instanceof Technician){
			return (Technician)s;
		}else{
			return null;
		}
	}
}
