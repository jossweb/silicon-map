package domain;

import java.util.ArrayList;
import java.util.List;

public class GpuCompute extends Machine{
	public GpuCompute(int id,String hostname, String ip_address, String mac_adress, String os, String status) {
		super(id, hostname, ip_address, mac_adress, os, status);
	}
	public static ArrayList<GpuCompute> GetGpuComputeFromStats(Statistics s){
		List<GpuCompute> l =  s.getMachines().stream()
							.filter(e->e instanceof GpuCompute)
							.map(e-> (GpuCompute) e)
            				.toList();
		return new ArrayList<GpuCompute>(l);
	}
}
