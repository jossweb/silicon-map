package domain;

import java.sql.ResultSet;

public class Technician extends Staff{
	public Technician(int id, String name, String firstname, String hash, String username, boolean available) {
		super(id, name, firstname, hash, username, available);
	}
	public Technician(ResultSet sqlResult){
		super(sqlResult);
	}
}
