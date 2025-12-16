package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class Compute extends Machine {
	public Compute(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	public Compute(ResultSet sqlResult){
		super(sqlResult);
	}
	public static ArrayList<Compute> GetComputeFromStats(Statistics s){
		List<Compute> l =  s.getMachines().stream()
							.filter(e->e instanceof Compute)
							.map(e-> (Compute) e)
            				.toList();
		return new ArrayList<Compute>(l);
	}
}
