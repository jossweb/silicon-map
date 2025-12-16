package domain;

import java.sql.ResultSet;

public class Router extends Network{
	public Router(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	public Router(ResultSet sqlResult){
		super(sqlResult);
	}
}
