package domain;

import java.sql.ResultSet;
public class Compute extends Machine{
	public Compute(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	public Compute(ResultSet sqlResult){
		super(sqlResult);
	}
}
