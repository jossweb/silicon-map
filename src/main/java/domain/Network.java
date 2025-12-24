package domain;

import java.sql.ResultSet;

public abstract class Network extends Machine{
    public Network(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	public Network(ResultSet sqlResult){
		super(sqlResult);
	}
}
