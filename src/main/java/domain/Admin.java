package domain;

import java.sql.ResultSet;

public class Admin extends Staff {
	public Admin(int id, String name, String firstname, String hash, String username, boolean available) {
		super(id, name, firstname, hash, username, available);
	}
	public Admin(ResultSet sqlResult){
		super(sqlResult);
	}
}
