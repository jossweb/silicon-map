package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class Network extends Machine{
    public Network(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	public Network(ResultSet sqlResult){
		super(sqlResult);
	}
    public static ArrayList<Network> GetNetworkFromStats(Statistics s){
		List<Network> l =  s.getMachines().stream()
							.filter(e->e instanceof Network)
							.map(e-> (Network) e)
            				.toList();
		return new ArrayList<Network>(l);
	}
}
