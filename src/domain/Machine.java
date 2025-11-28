package domain;

public class Machine {
	private String hostname;
	private String ip_address;
	private String mac_adress;
	private String os;
	private String status;
	
	public Machine(String hostname, String ip_address, String mac_adress, String os, String status) {
		this.hostname=hostname;
		this.ip_address=ip_address;
		this.mac_adress=mac_adress;
		this.os=os;
		this.status=status;
	}
	public Machine(String hostname, String mac_adress, String os, String status) {
		this.hostname=hostname;
		this.ip_address=null;
		this.mac_adress=mac_adress;
		this.os=os;
		this.status=status;
	}
	// =============================
	// TODO : 
	// Implement methods
	// =============================
}
