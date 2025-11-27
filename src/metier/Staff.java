package metier;

public abstract class Staff {
	private int id;
	private String name;
	private String first_name;
	
	public Staff(int id, String name, String firstname) {
		this.id = id;
		this.name = name;
		this.first_name = firstname;
	}
	
	// =============================
	// TODO : 
	// Implement methods and add daughter 
	// class 'admin' and 'technician'
	// =============================
}
