package domain;

import java.util.ArrayList;
import java.util.List;

public class Storage extends Machine{
	public Storage(int id, String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	public static ArrayList<Storage> GetStorageFromStats(Statistics s){
		List<Storage> l =  s.getMachines().stream()
							.filter(e->e instanceof Storage)
							.map(e-> (Storage) e)
            				.toList();
		return new ArrayList<Storage>(l);
	}
}
