package domain;

import java.sql.ResultSet;

public class Switch extends Network{
	public Switch(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	public Switch(ResultSet sqlResult){
		super(sqlResult);
	}
}
