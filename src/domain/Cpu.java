package domain;

public class Cpu extends Component{
	private int nb_core;
	private int max_ram;
	public Cpu(String brand, String model, Machine m, int nb_core, int max_ram) {
		super(brand, model, m);
		this.nb_core = nb_core;
		this.max_ram = max_ram;
	}
}
