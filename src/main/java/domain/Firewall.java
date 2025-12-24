package domain;

import java.sql.ResultSet;
public class Firewall extends Network{
	public Firewall(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	public Firewall(ResultSet sqlResult){
		super(sqlResult);
	}
}
