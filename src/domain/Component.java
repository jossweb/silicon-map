package domain;

public abstract class Component {
	private String brand;
	private String model;
	private Machine machine;
	
	public Component(String brand, String model, Machine m) {
		this.brand = brand;
		this.model = model;
		this.machine = m;
	}
}
